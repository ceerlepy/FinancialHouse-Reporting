package uk.financial.reporting.service;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.login.bean.LoginRequest;
import uk.financial.reporting.login.bean.LoginResponse;

public interface MerchantLoginService {

    LoginResponse login(LoginRequest user) 
    		throws LoginException, SystemException, RemoteSystemException;
}
