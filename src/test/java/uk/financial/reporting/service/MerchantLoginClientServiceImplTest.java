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
import uk.financial.reporting.login.bean.LoginRequest;
import uk.financial.reporting.login.bean.LoginResponse;
import uk.financial.reporting.rest.RestServiceImpl;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockRestServiceServer
@ContextConfiguration(classes = { RestServiceImpl.class, RestTemplateResponseErrorHandler.class })
@RestClientTest(RestServiceImpl.class)
@TestPropertySource("classpath:test.properties")
public class MerchantLoginClientServiceImplTest {

    @Autowired
    private RestServiceImpl<LoginRequest, LoginResponse> restService;

    @Autowired
    private MockRestServiceServer server;

    @Test
    public void sendLoginRequest_shouldParseRepsonseBody_when500Error() throws Exception{

        this.server
                .expect(requestTo(LOGIN_URL))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withServerError().body(DECLINED_RESPONSE).contentType(MediaType.APPLICATION_JSON));

        LoginResponse response = restService.sendRequest(INVALID_LOGIN_REQUEST,
        						LoginResponse.class, LOGIN_URL, true, null);

        assertThat(response.getStatus()).isEqualTo("DECLINED");
        assertThat(response.getToken()).isNull();
    }

    private static final String LOGIN_URL = "/test/login";
    private static final LoginRequest INVALID_LOGIN_REQUEST = LoginRequest.builder().build();
    private static final String DECLINED_RESPONSE = "{\n" +
            "    \"code\": 0,\n" +
            "    \"status\": \"DECLINED\",\n" +
            "    \"message\": \"Error: Merchant User credentials is not valid\"\n" +
            "}";

}