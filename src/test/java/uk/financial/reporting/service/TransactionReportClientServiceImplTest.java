package uk.financial.reporting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;

import uk.financial.reporting.handler.RestTemplateResponseErrorHandler;
import uk.financial.reporting.rest.RestServiceImpl;
import uk.financial.reporting.transactionreport.bean.TransactionReportRequest;
import uk.financial.reporting.transactionreport.bean.TransactionReportResponse;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockRestServiceServer
@RestClientTest(RestServiceImpl.class)
@ContextConfiguration(classes = {
		RestServiceImpl.class,
		TransactionReportResponse.class,
		RestTemplateResponseErrorHandler.class, 
		String.class})
@TestPropertySource("classpath:test.properties")
public class TransactionReportClientServiceImplTest {

    @Autowired
    private RestServiceImpl<TransactionReportRequest, TransactionReportResponse> restService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void retrieveTransactionsReport_shouldParseRepsonseBody_when500Error() throws Exception {

        this.server
                .expect(requestTo(TRANSACTION_REPORT_URL))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withServerError().contentType(MediaType.APPLICATION_JSON).body(DECLINED_RESPONSE));

        TransactionReportResponse response = restService.sendRequest(INVALID_TRANSACTION_REPORT_REQUEST,
        		TransactionReportResponse.class, TRANSACTION_REPORT_URL, false, TOKEN);

        assertThat(response.getStatus()).isEqualTo(DECLINED_STATUS);
    }

    private static final String DECLINED_STATUS = "DECLINED";
    private static final String TRANSACTION_REPORT_URL = "/api/transactions/report";
    private static final String TOKEN = "token";
    private static final TransactionReportRequest INVALID_TRANSACTION_REPORT_REQUEST = TransactionReportRequest.builder().token(TOKEN).build();
    private static final String DECLINED_RESPONSE = "{\n" +
            "    \"code\": 0,\n" +
            "    \"status\": \"DECLINED\",\n" +
            "    \"message\": \"Error: Merchant User credentials is not valid\"\n" +
            "}";

}