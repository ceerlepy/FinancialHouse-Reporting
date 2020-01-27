package uk.financial.reporting.service;

import uk.financial.reporting.client.ClientRequest;
import uk.financial.reporting.client.ClientResponse;
import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;

public interface ClientService {

    ClientResponse retrieveClient(ClientRequest request) 
    		throws LoginException, SystemException, RemoteSystemException;
}
