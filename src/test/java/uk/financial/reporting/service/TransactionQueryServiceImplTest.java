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
import uk.financial.reporting.transactionquery.bean.TransactionQueryRequest;
import uk.financial.reporting.transactionquery.bean.TransactionQueryResponse;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = TestConfiguration.class)
public class TransactionQueryServiceImplTest {

    @InjectMocks
    @Autowired
    private TransactionQueryServiceImpl transactionQueryService;
    
    @MockBean
    private RestService<TransactionQueryRequest, TransactionQueryResponse> restService;

    @Test
    public void retrieveReportList_shouldNotThrowAnyException_whenRemoteSystemAccessAccepted() throws Exception{

        when(restService.sendRequest(any(TransactionQueryRequest.class), 
        		eq(TransactionQueryResponse.class), anyString(), eq(false), eq(null)))
                .thenReturn(TRANSACTION_QUERY_ACCEPTED_RESPONSE);

        assertThatCode(() -> {
            transactionQueryService.retrieveTransactions(TRANSACTION_QUERY_REQUEST_WITH_VALID_TOKEN);
        }).doesNotThrowAnyException();

    }

    @Test
    public void retrieveReportList_shouldThrowLoginException_whenRemoteSystemAccessDeclined() throws Exception{

        when(restService.sendRequest(any(TransactionQueryRequest.class),
        		eq(TransactionQueryResponse.class), anyString(), eq(false), eq("0")))
        		.thenReturn(TRANSACTION_QUERY_DECLINED_RESPONSE);

        assertThatThrownBy(() -> {
                transactionQueryService.retrieveTransactions(TRANSACTION_QUERY_REQUEST_WITH_EXPIRED_TOKEN);
        }).isInstanceOf(LoginException.class);

    }

    @Test
    public void retrieveReportList_shouldRemoteSystemLoginException_whenRemoteSystemAccessNotApprovedOrNotDeclined() throws Exception{

        when(restService.sendRequest(any(TransactionQueryRequest.class),
        		eq(TransactionQueryResponse.class), anyString(), eq(false), anyString()))
        	.thenReturn(TRANSACTION_QUERY_ERROR_RESPONSE);

        assertThatThrownBy(() -> {
            transactionQueryService.retrieveTransactions(TRANSACTION_QUERY_REQUEST_WITH_VALID_TOKEN);
        }).isInstanceOf(RemoteSystemException.class);

    }

    private static final String DECLINED = "DECLINED";
    private static final String ERROR = "ERROR";
    
    private static final TransactionQueryRequest TRANSACTION_QUERY_REQUEST_WITH_VALID_TOKEN = TransactionQueryRequest.builder().build();
    private static final TransactionQueryRequest TRANSACTION_QUERY_REQUEST_WITH_EXPIRED_TOKEN = 
    		TransactionQueryRequest.builder().token("0").build();
    
    private static final TransactionQueryResponse TRANSACTION_QUERY_DECLINED_RESPONSE = TransactionQueryResponse.builder().status(DECLINED).build();
    private static final TransactionQueryResponse TRANSACTION_QUERY_ERROR_RESPONSE = TransactionQueryResponse.builder().status(ERROR).build();
    private static final TransactionQueryResponse TRANSACTION_QUERY_ACCEPTED_RESPONSE = TransactionQueryResponse.builder().build();

}