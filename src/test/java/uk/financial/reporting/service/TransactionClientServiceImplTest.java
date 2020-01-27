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
import uk.financial.reporting.transaction.bean.TransactionRequest;
import uk.financial.reporting.transaction.bean.TransactionResponse;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockRestServiceServer
@RestClientTest(RestServiceImpl.class)
@ContextConfiguration(classes = {RestServiceImpl.class, RestTemplateResponseErrorHandler.class})
@TestPropertySource("classpath:test.properties")
public class TransactionClientServiceImplTest {

    @Autowired
    private RestServiceImpl<TransactionRequest, TransactionResponse> restService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void retrieveTransaction_shouldParseRepsonseBody_when500Error() throws Exception{

        this.server
                .expect(requestTo(TRANSACTION_URL))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withServerError()
                        .contentType(MediaType.APPLICATION_JSON).body(DECLINED_RESPONSE));

        TransactionResponse response = restService.sendRequest(REQUEST,
        								TransactionResponse.class, TRANSACTION_URL, false, TOKEN);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("DECLINED");

    }

    private static final String APPROVED = "APPROVED";
    private static final TransactionRequest REQUEST = TransactionRequest.builder().build();
    private static final String TRANSACTION_URL = "/api/transaction";
    private static final String TOKEN = "token";

    private static final String DECLINED_RESPONSE = "{\n" +
            "    \"code\": 0,\n" +
            "    \"status\": \"DECLINED\",\n" +
            "    \"message\": \"Error: Merchant User credentials is not valid\"\n" +
            "}";

    private static final String APPROVED_RESPONSE = "{\n" +
            "   \"fx\":{\n" +
            "      \"merchant\":{\n" +
            "         \"originalAmount\":100,\n" +
            "         \"originalCurrency\":\"EUR\"\n" +
            "      }\n" +
            "   },\n" +
            "   \"customerInfo\":{\n" +
            "      \"id\":1,\n" +
            "      \"created_at\":\"2015-10-09 12:09:10\",\n" +
            "      \"updated_at\":\"2015-10-09 12:09:10\",\n" +
            "      \"deleted_at\":null,\n" +
            "      \"number\":\"401288XXXXXX1881\",\n" +
            "      \"expiryMonth\":\"6\",\n" +
            "      \"expiryYear\":\"2017\",\n" +
            "      \"startMonth\":null,\n" +
            "      \"startYear\":null,\n" +
            "      \"issueNumber\":null,\n" +
            "      \"email\":\"michael@gmail.com\",\n" +
            "      \"birthday\":\"1986-03-20 12:09:10\",\n" +
            "      \"gender\":null,\n" +
            "      \"billingTitle\":null,\n" +
            "      \"billingFirstName\":\"Michael\",\n" +
            "      \"billingLastName\":\"Kara\",\n" +
            "      \"billingCompany\":null,\n" +
            "      \"billingAddress1\":\"test address\",\n" +
            "      \"billingAddress2\":null,\n" +
            "      \"billingCity\":\"Antalya\",\n" +
            "      \"billingPostcode\":\"07070\",\n" +
            "      \"billingState\":null,\n" +
            "      \"billingCountry\":\"TR\",\n" +
            "      \"billingPhone\":null,\n" +
            "      \"billingFax\":null,\n" +
            "      \"shippingTitle\":null,\n" +
            "      \"shippingFirstName\":\"Michael\",\n" +
            "      \"shippingLastName\":\"Kara\",\n" +
            "      \"shippingCompany\":null,\n" +
            "      \"shippingAddress1\":\"test   address\",\n" +
            "      \"shippingAddress2\":null,\n" +
            "      \"shippingCity\":\"Antalya\",\n" +
            "      \"shippingPostcode\":\"07070\",\n" +
            "      \"shippingState\":null,\n" +
            "      \"shippingCountry\":\"TR\",\n" +
            "      \"shippingPhone\":null,\n" +
            "      \"shippingFax\":null\n" +
            "   },\n" +
            "   \"merchant\":{\n" +
            "      \"name\":\"Dev-Merchant\"\n" +
            "   },\n" +
            "   \"transaction\":{\n" +
            "      \"merchant\":{\n" +
            "         \"referenceNo\":\"reference_5617ae66281ee\",\n" +
            "         \"merchantId\":1,\n" +
            "         \"status\":\"WAITING\",\n" +
            "         \"channel\":\"API\",\n" +
            "         \"customData\":null,\n" +
            "         \"chainId\":\"5617ae666b4cb\",\n" +
            "         \"agentInfoId\":1,\n" +
            "         \"operation\":\"DIRECT\",\n" +
            "         \"fxTransactionId\":1,\n" +
            "         \"updated_at\":\"2015-10-09 12:09:12\",\n" +
            "         \"created_at\":\"2015-10-09 12:09:10\",\n" +
            "         \"id\":1,\n" +
            "         \"acquirerTransactionId\":1,\n" +
            "         \"code\":\"00\",\n" +
            "         \"message\":\"Waiting\",\n" +
            "         \"transactionId\":\"1-1444392550-1\",\n" +
            "         \"agentResponse\":{\n" +
            "            \"id\":1,\n" +
            "            \"customerIp\":\"192.168.1.2\",\n" +
            "            \"customerUserAgent\":\"AgentResponse\",\n" +
            "            \"merchantIp\":\"127.0.0.1\"\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}";

}