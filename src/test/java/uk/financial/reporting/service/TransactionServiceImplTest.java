package uk.financial.reporting.service;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.rest.RestService;
import uk.financial.reporting.transaction.bean.TransactionRequest;
import uk.financial.reporting.transaction.bean.TransactionResponse;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = TestConfiguration.class)
public class TransactionServiceImplTest {

    @InjectMocks
    @Autowired
    private TransactionServiceImpl transactionService;

    @MockBean
    private RestService<TransactionRequest, TransactionResponse> restService;

    @Test
    public void retrieveTransaction_shouldNotThrowException_whenRemoteSystemAccessAceepted() throws Exception {

        when(restService.sendRequest(any(TransactionRequest.class),
        		eq(TransactionResponse.class), anyString(), eq(false), eq(null)))
                .thenReturn(APPROVED_RESPONSE);

        assertThatCode(() -> {
            transactionService.retrieveTransaction(TRANSACTION_QUERY_REQUEST_WITH_VALID_TOKEN);
        }).doesNotThrowAnyException();

    }

    @Test
    public void retrieveTransaction_shouldThrowLoginException_whenRemoteSystemAccessDeclined() throws Exception{

        when(restService.sendRequest(any(TransactionRequest.class),
        		eq(TransactionResponse.class), anyString(), eq(false), eq(null)))
        	.thenReturn(DECLINED_RESPONSE);

        assertThatThrownBy(() -> {
            transactionService.retrieveTransaction(TRANSACTION_QUERY_REQUEST_WITH_INVALID_TOKEN);
        }).isInstanceOf(LoginException.class);

    }

    @Test
    public void retrieveTransaction_shouldThrowRemoteSystemException_whenRemoteSystemAccessNotAcceptedOrDeclined() throws Exception  {

        when(restService.sendRequest(any(TransactionRequest.class),
        		eq(TransactionResponse.class), anyString(), eq(false), anyString()))
        	.thenReturn(ERROR_RESPONSE);


        assertThatThrownBy(() -> {
            transactionService.retrieveTransaction(TRANSACTION_QUERY_REQUEST_WITH_VALID_TOKEN);
        }).isInstanceOf(RemoteSystemException.class);

    }

    private  static final String APPROVED = "APPROVED";
    
    private static final String DECLINED_STATUS = "DECLINED";
    private static final String ERROR_STATUS = "ERROR";
    
    private static final String DECLINED_MESSAGE = "invalid token";
    private static final String ERROR_MESSAGE = "there is no token";

    private static final TransactionRequest TRANSACTION_QUERY_REQUEST_WITH_VALID_TOKEN = TransactionRequest.builder().build();
    private static final TransactionRequest TRANSACTION_QUERY_REQUEST_WITH_INVALID_TOKEN = TransactionRequest.builder().build();


    private static final TransactionResponse APPROVED_RESPONSE =  TransactionResponse.builder()
    		.status(APPROVED)
            .build();

    private static final TransactionResponse ERROR_RESPONSE =  TransactionResponse.builder()
            .status(ERROR_STATUS)
            .message(ERROR_MESSAGE)
            .build();

    private static final TransactionResponse DECLINED_RESPONSE = TransactionResponse.builder()
            .status(DECLINED_STATUS)
            .message(DECLINED_MESSAGE)
            .build();
}