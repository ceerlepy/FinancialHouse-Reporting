package uk.financial.reporting.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import uk.financial.reporting.service.TransactionQueryService;
import uk.financial.reporting.transactionquery.bean.TransactionQueryRequest;
import uk.financial.reporting.transactionquery.bean.TransactionQueryResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TransactionQueryController.class)
public class TransactionQueryControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TransactionQueryService transactionQueryService;

    @Test
    public void query_shouldReturnResponseWithHttpStatus200WithContent_whenUserCredentialsIsValid() throws Exception {

        when(transactionQueryService.retrieveTransactions(Mockito.any(TransactionQueryRequest.class)))
        		.thenReturn(TRANSACTION_QUERY_RESPONSE);

         mvc.perform(post(TRANSACTION_QUERY_URL)
        	   .with(csrf())
               .contentType(MediaType.APPLICATION_JSON)
               .content(TRANSACTION_QUERY_INPUT)
               .header("token", "token"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.per_page").value(PER_PAGE));

    }

    @Test
    public void query_shouldReturnResponseWithHttpStatus401_whenTokenIsExpired() throws Exception {

        Mockito.doThrow(LoginException.class).when(transactionQueryService)
        		.retrieveTransactions(Mockito.any(TransactionQueryRequest.class));

        mvc.perform(post(TRANSACTION_QUERY_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TRANSACTION_QUERY_INPUT)
                .header("token", "token"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void query_shouldReturnResponseWithHttpStatus500_whenUnexpectedErrorOccurs() throws Exception {

        Mockito.doThrow(RemoteSystemException.class).when(transactionQueryService)
        		.retrieveTransactions(Mockito.any(TransactionQueryRequest.class));

        mvc.perform(post(TRANSACTION_QUERY_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(TRANSACTION_QUERY_INPUT)
                .header("token", "token"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void retrieveTransactionReport_shouldReturnResponseWithHttpStatus400_whenInvalidInputReceives() throws Exception {

        mvc.perform(post(TRANSACTION_QUERY_URL)
                .content(EMPTY_TRANSACTION_QUERY_INPUT)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fromDate", is("fromDate is mandatory")))
                .andExpect(jsonPath("$.toDate", is("toDate is mandatory")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private static final long PER_PAGE = 234;
    private static final String TRANSACTION_QUERY_URL = "/api/transactions/query";
    private static final String TRANSACTION_QUERY_INPUT = "{\"fromDate\":\"fromDate\", \"toDate\": \"toDate\", \"status\": \"status\"}";
    private static final String EMPTY_TRANSACTION_QUERY_INPUT = "{\"\" : \"\"}";

    private static final TransactionQueryResponse TRANSACTION_QUERY_RESPONSE = TransactionQueryResponse.builder()
            .perPage(PER_PAGE)
            .build();

}
