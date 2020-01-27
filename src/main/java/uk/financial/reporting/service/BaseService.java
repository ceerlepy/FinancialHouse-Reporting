package uk.financial.reporting.service;

import org.springframework.web.client.ResourceAccessException;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;

public class BaseService {

	public void handleException(Exception ex) throws LoginException, SystemException, RemoteSystemException {

		if (ex instanceof LoginException) {
			throw (LoginException) ex;
		} else if (ex instanceof SystemException) {
			throw (SystemException) ex;
		} else {
			if (ex != null && ex instanceof ResourceAccessException
					&& ex.getCause() instanceof java.net.HttpRetryException) {
				throw new RemoteSystemException("Token is expired", ex);
			}
			throw new RemoteSystemException("System exception", ex);
		}
	}

}
