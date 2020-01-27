package uk.financial.reporting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import uk.financial.reporting.common.Statics.Status;
import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.login.bean.LoginRequest;
import uk.financial.reporting.login.bean.LoginResponse;
import uk.financial.reporting.rest.RestService;

@Service
public class MerchantLoginServiceImpl extends BaseService implements MerchantLoginService {

	private RestService<LoginRequest, LoginResponse> restService;
	private String loginUrl;

	@Autowired
	public MerchantLoginServiceImpl(RestService<LoginRequest, LoginResponse> restService,
			@Value("${reporting.api.login.url}") String loginUrl) {
		this.restService = restService;
		this.loginUrl = loginUrl;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) 
			throws LoginException, SystemException, RemoteSystemException{

		LoginResponse loginResponse = null;
		try {
			loginResponse = restService.sendRequest(loginRequest, LoginResponse.class, loginUrl, true, null);

			if (Status.APPROVED.equals(loginResponse.getStatus())) {
				return loginResponse;
			}

			if (Status.DECLINED.equals(loginResponse.getStatus())) {
				throw new LoginException(loginResponse.getMessage());
			}

			if (!Status.APPROVED.toString().equals(loginResponse.getStatus())
					&& (loginResponse.getStatus() != null && !loginResponse.getStatus().isEmpty())) {
				throw new SystemException(
						"Merchant Login Request Invocation Received Unexpected Status. Status-Message: "
								+ loginResponse.getStatus() + "-" + loginResponse.getMessage());
			}

		} catch (Exception ex) {
			handleException(ex);
		}

		return loginResponse;

	}
}
