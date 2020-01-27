package uk.financial.reporting.controller;


import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doThrow;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.login.bean.LoginRequest;
import uk.financial.reporting.login.bean.LoginResponse;
import uk.financial.reporting.service.MerchantLoginService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MerchantLoginController.class)
public class MerchantLoginControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private MerchantLoginService merchantLoginService;

    @Test
    public void loginUser_shouldReturnResponseWithHttpStatus200_whenUserCredentialsIsValid() throws Exception {

        when(merchantLoginService.login(Mockito.any(LoginRequest.class))).thenReturn(LOGIN_RESPONSE);

        mvc.perform(post(LOGIN_PATH)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(USER_WITH_VALID_CREDENTIALS)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(TOKEN));
    }

    @Test
    public void loginUser_shouldReturnResponseWithHttpStatus401_whenUserCredentialsIsInvalid() throws Exception {

        doThrow(LoginException.class).when(merchantLoginService).login(Mockito.any(LoginRequest.class));

        mvc.perform(post(LOGIN_PATH)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_LOGIN_INPUT))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void loginUser_shouldReturnResponseWithHttpStatus500_whenUnexpectedErrorOccurs() throws Exception {

        doThrow(RemoteSystemException.class).when(merchantLoginService).login(Mockito.any(LoginRequest.class));

        mvc.perform(post(LOGIN_PATH)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(USER_LOGIN_INPUT))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void loginUser_shouldReturnResponseWithHttpStatus400_whenInvalidInputReceives() throws Exception {

        mvc.perform(post(LOGIN_PATH)
                .content(asJsonString(USER_WITH_INVALID_CREDENTIALS))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email", is("E-Mail is mandatory")))
                .andExpect(jsonPath("$.password", is("Password is mandatory")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private static final String LOGIN_PATH = "/api/merchant/login";
    private static final String E_MAIL = "test@mail.com";
    private static final String PASSWORD = "12345";
    private static final String TOKEN = "token";
    private static final String USER_LOGIN_INPUT = "{\"email\": \"email\", \"password\" : \"password\"}";
    private static final LoginRequest USER_WITH_VALID_CREDENTIALS = LoginRequest.builder()
    							.email(E_MAIL)
    							.password(PASSWORD).build();
    private static final LoginRequest USER_WITH_INVALID_CREDENTIALS = LoginRequest.builder().build();
    private static final LoginResponse LOGIN_RESPONSE = LoginResponse.builder().token(TOKEN).build();

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}