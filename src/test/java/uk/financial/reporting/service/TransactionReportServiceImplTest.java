package uk.financial.reporting.service;


import static org.assertj.core.api.Assertions.assertThat;
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
import uk.financial.reporting.transactionreport.bean.TransactionReportRequest;
import uk.financial.reporting.transactionreport.bean.TransactionReportResponse;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = TestConfiguration.class)
public class TransactionReportServiceImplTest {

    @InjectMocks
    @Autowired
    private TransactionReportServiceImpl transactionReportService;
    
    @MockBean
    private RestService<TransactionReportRequest, TransactionReportResponse> restService;

    @Test
    public void retrieveReportList_shouldThrowLoginException_whenRemoteSystemAccessDenied() throws Exception {

        when(restService.sendRequest(any(TransactionReportRequest.class),
        		eq(TransactionReportResponse.class), anyString(), eq(false), eq("0")))
                .thenReturn(TRANSACTION_REPORT_DECLINED_RESPONSE);

        assertThatThrownBy(() -> {
            transactionReportService.retrieveReportList(TRANSACTION_REPORT_REQUEST_WITH_EXPIRED_TOKEN);
        }).isInstanceOf(LoginException.class);

    }

    @Test
    public void retrieveReportList_shouldThrowRemoteSystemAccess_whenRemoteSystemAccessNotApprovedOrNotDeclined() throws Exception  {

        when(restService.sendRequest(any(TransactionReportRequest.class),
        		eq(TransactionReportResponse.class), anyString(), eq(false), eq("0")))
                .thenReturn(TRANSACTION_REPORT_ERROR_RESPONSE);

        assertThatThrownBy(() -> {
            transactionReportService.retrieveReportList(TRANSACTION_REPORT_REQUEST_WITH_VALID_TOKEN);
        }).isInstanceOf(RemoteSystemException.class);

    }

    @Test
    public void retrieveReportList_shouldReturnTransactionReportList_whenRemoteSystemAccessAccepted() throws Exception {

        when(restService.sendRequest(any(TransactionReportRequest.class),
        		eq(TransactionReportResponse.class), anyString(), eq(false), eq(TOKEN)))
                .thenReturn(TRANSACTION_REPORT_APPROVED_RESPONSE);

        TransactionReportResponse transactionReportResponse  = 
        		transactionReportService.retrieveReportList(TRANSACTION_REPORT_REQUEST_WITH_VALID_TOKEN);

        assertThat(transactionReportResponse.getResponseList()).isNotNull();
        assertThat(transactionReportResponse.getResponseList()).hasSize(1);

    }


    private static final String DECLINED = "DECLINED";
    private static final String ERROR = "ERROR";
    private static final int COUNT = 1;
    private static final int TOTAL = 11;
    private static final String CURRENCY = "GBP";
    private static final String TOKEN = "token";
    
    private static final TransactionReportRequest TRANSACTION_REPORT_REQUEST_WITH_VALID_TOKEN = 
    		TransactionReportRequest.builder().token(TOKEN).build();
    private static final TransactionReportRequest TRANSACTION_REPORT_REQUEST_WITH_EXPIRED_TOKEN = 
    		TransactionReportRequest.builder().token("0").build();

    private static final TransactionReportResponse TRANSACTION_REPORT_ERROR_RESPONSE = TransactionReportResponse.builder()
            .status(ERROR).build();

    private static final TransactionReportResponse TRANSACTION_REPORT_DECLINED_RESPONSE = TransactionReportResponse.builder()
            .status(DECLINED).build();

    private static final TransactionReportResponse TRANSACTION_REPORT_APPROVED_RESPONSE = TransactionReportResponse.builder()
            .response(TransactionReportResponse.Response.builder()
                    .count(COUNT)
                    .total(TOTAL)
                    .currency(CURRENCY)
                    .build()
            )
            .build();

}