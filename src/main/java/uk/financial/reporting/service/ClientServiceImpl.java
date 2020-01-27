package uk.financial.reporting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import uk.financial.reporting.client.ClientRequest;
import uk.financial.reporting.client.ClientResponse;
import uk.financial.reporting.common.Statics.Status;
import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.rest.RestService;

@Service
public class ClientServiceImpl extends BaseService implements ClientService {

	private RestService<ClientRequest, ClientResponse> restService;
	private String url;

	@Autowired
	public ClientServiceImpl(RestService<ClientRequest, ClientResponse> restService,
			@Value("${reporting.api.client.url}") String url) {
		this.restService = restService;
		this.url = url;
	}

	@Override
	public ClientResponse retrieveClient(ClientRequest request)
			throws LoginException, SystemException, RemoteSystemException {

		ClientResponse response = null;
		
		try {
			response = restService.sendRequest(request, ClientResponse.class, url, false,
					request.getToken());
	
			if (Status.DECLINED.equals(response.getStatus())) {
				throw new LoginException("Transaction Request Declined. Message: " + response.getMessage());
			}
	
			if (!Status.APPROVED.equals(response.getStatus()) 
					&& (response.getStatus() != null && !response.getStatus().isEmpty())) {
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
