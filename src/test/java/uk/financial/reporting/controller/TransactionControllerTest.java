package uk.financial.reporting.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.service.TransactionService;
import uk.financial.reporting.transaction.bean.ExchangeMerchantResponse;
import uk.financial.reporting.transaction.bean.ExchangeResponse;
import uk.financial.reporting.transaction.bean.TransactionRequest;
import uk.financial.reporting.transaction.bean.TransactionResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TransactionController.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TransactionService transactionService;

	@Test
	public void retrieveTransaction_shouldReturnResponseWithHttpStatus200_whenUserCredentialsIsValid()
			throws Exception {
		
		when(transactionService.retrieveTransaction(Mockito.any(TransactionRequest.class)))
				.thenReturn(TRANSACTION_OUTPUT);

		mvc.perform(post(TRANSACTION_URL)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(TRANSACTION_INPUT_WITH_VALID_TOKEN)
				.header("token", "token"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.exchangeResponse.merchant.originalAmount").value(ORIGINAL_AMOUNT))
				.andExpect(jsonPath("$.exchangeResponse.merchant.originalCurrency").value(ORIGINAL_CURRENCY));
	}

	@Test
	public void retrieveTransaction_shouldReturnResponseWithHttpStatus401_whenUserCredentialsIsInvalid()
			throws Exception {

		Mockito.doThrow(LoginException.class).when(transactionService)
				.retrieveTransaction(Mockito.any(TransactionRequest.class));

		mvc.perform(post(TRANSACTION_URL)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(TRANSACTION_INPUT_WITH_EXPIRED_TOKEN)
				.header("token", 0))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void retrieveTransaction_shouldReturnResponseWithHttpStatus500_whenUnexpectedErrorOccurs() throws Exception {

		Mockito.doThrow(RemoteSystemException.class).when(transactionService)
				.retrieveTransaction(Mockito.any(TransactionRequest.class));

		mvc.perform(post(TRANSACTION_URL)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(TRANSACTION_INPUT_WITH_EXPIRED_TOKEN)
				.header("token", 0))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void retrieveTransaction_shouldReturnResponseWithHttpStatus400_whenInvalidInputReceives() throws Exception {

		mvc.perform(post(TRANSACTION_URL)
				.content(EMPTY_TRANSACTION_INPUT)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.transactionId", is("Transaction id is mandatory")))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	private static final String TRANSACTION_URL = "/api/transaction";

	private static final String TRANSACTION_INPUT_WITH_VALID_TOKEN = "{\"transactionId\": \"transactionId\"}";
	private static final String TRANSACTION_INPUT_WITH_EXPIRED_TOKEN = "{\"transactionId\": \"1\"}";
	private static final String EMPTY_TRANSACTION_INPUT = "{\"transactionId\": \"\"}";
	
	private static final BigDecimal ORIGINAL_AMOUNT = BigDecimal.ZERO;
	private static final String ORIGINAL_CURRENCY = "EUR";
	private static final TransactionResponse TRANSACTION_OUTPUT = TransactionResponse.builder()
			.fx(Optional
					.of(ExchangeResponse
							.builder().merchant(Optional.of(ExchangeMerchantResponse.builder()
									.originalCurrency(ORIGINAL_CURRENCY).originalAmount(ORIGINAL_AMOUNT).build()))
							.build()))
			.build();
}