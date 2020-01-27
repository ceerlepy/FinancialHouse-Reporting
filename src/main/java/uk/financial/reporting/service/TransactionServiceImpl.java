package uk.financial.reporting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import uk.financial.reporting.common.Statics.Status;
import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.rest.RestService;
import uk.financial.reporting.transaction.bean.TransactionRequest;
import uk.financial.reporting.transaction.bean.TransactionResponse;

@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {

	private RestService<TransactionRequest, TransactionResponse> restService;
	private String url;

	@Autowired
	public TransactionServiceImpl(RestService<TransactionRequest, TransactionResponse> restService,
			@Value("${reporting.api.transaction.url}") String url) {
		this.restService = restService;
		this.url = url;
	}

	@Override
	public TransactionResponse retrieveTransaction(TransactionRequest request)
			throws LoginException, SystemException, RemoteSystemException {

		TransactionResponse response = null;
		
		try {
			response = restService.sendRequest(request, TransactionResponse.class, url, false,
					request.getToken());
	
			if (Status.DECLINED.equals(response.getStatus())) {
				throw new LoginException("Transaction Request Declined. Message: " + response.getMessage());
			}
	
			if (!Status.APPROVED.equals(response.getStatus()) && 
					(response.getStatus() != null && !response.getStatus().isEmpty())) {
				throw new SystemException(
						"Transaction Request Invocation Received Unexpected Status. Status-Message: " + response.getStatus()
								+ "-" + response.getMessage());
			}
		}catch(Exception ex) {
			handleException(ex);
    	}

		return response;

	}
}
