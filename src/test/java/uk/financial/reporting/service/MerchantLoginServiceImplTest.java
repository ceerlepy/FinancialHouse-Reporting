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
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.login.bean.LoginRequest;
import uk.financial.reporting.login.bean.LoginResponse;
import uk.financial.reporting.rest.RestService;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration(classes = TestConfiguration.class)
public class MerchantLoginServiceImplTest {

    @InjectMocks
    @Autowired
    private MerchantLoginServiceImpl merchantLoginService;
    
    @MockBean
    private RestService<LoginRequest, LoginResponse> restService;

    @Test
    public void login_shouldNotThrowAnyException_whenLoginRequestAccepted() throws Exception {

        when(restService.sendRequest(any(LoginRequest.class), eq(LoginResponse.class), anyString(), eq(true), eq(null)))
        	.thenReturn(LOGIN_APPROVED_RESPONSE);

        assertThatCode(() -> {
            merchantLoginService.login(VALID_LOGIN_REQUEST);
        }).doesNotThrowAnyException();

    }

    @Test
    public void login_shouldThrowLoginException_whenCredentialsDeclined() throws Exception  {

        when(restService.sendRequest(any(LoginRequest.class), eq(LoginResponse.class), anyString(), eq(true), eq(null)))
        	.thenReturn(LOGIN_DECLINED_RESPONSE);

        assertThatThrownBy(() -> {
            merchantLoginService.login(INVALID_LOGIN_REQUEST);
        }).isInstanceOf(LoginException.class);

    }

    @Test
    public void login_shouldThrowRemoteSystemException_whenExpectedErrorOccured() throws Exception  {

        when(restService.sendRequest(any(LoginRequest.class), eq(LoginResponse.class), anyString(), eq(true), eq(null)))
        	.thenReturn(LOGIN_ERROR_RESPONSE);

        assertThatThrownBy(() -> {
            merchantLoginService.login(VALID_LOGIN_REQUEST);
        }).isInstanceOf(SystemException.class);

    }

    private static final String DECLINED = "DECLINED";
    private static final String APPROVED = "APPROVED";
    private static final String ERROR = "ERROR";
    private static final LoginRequest VALID_LOGIN_REQUEST = LoginRequest.builder().build();
    private static final LoginRequest INVALID_LOGIN_REQUEST = LoginRequest.builder().build();
    
    private static final LoginResponse LOGIN_APPROVED_RESPONSE = LoginResponse.builder()
            .status(APPROVED)
            .build();
    
    private static final LoginResponse LOGIN_DECLINED_RESPONSE = LoginResponse.builder()
            .status(DECLINED)
            .build();

    private static final LoginResponse LOGIN_ERROR_RESPONSE = LoginResponse.builder()
            .status(ERROR)
            .build();



}
