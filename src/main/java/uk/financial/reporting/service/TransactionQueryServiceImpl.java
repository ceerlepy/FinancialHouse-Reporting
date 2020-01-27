package uk.financial.reporting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import uk.financial.reporting.common.Statics.Status;
import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.rest.RestService;
import uk.financial.reporting.transactionquery.bean.TransactionQueryRequest;
import uk.financial.reporting.transactionquery.bean.TransactionQueryResponse;

@Service
public class TransactionQueryServiceImpl extends BaseService implements TransactionQueryService {

    private RestService<TransactionQueryRequest, TransactionQueryResponse> restService;
    private String url;
    
    @Autowired
    public TransactionQueryServiceImpl(RestService<TransactionQueryRequest, TransactionQueryResponse> restService,
    		@Value("${reporting.api.transaction.query.url}") String url){
        this.restService = restService;
        this.url = url;
    }

    @Override
    public TransactionQueryResponse retrieveTransactions(TransactionQueryRequest request) 
    		throws LoginException, SystemException, RemoteSystemException {

    	TransactionQueryResponse response = null;
    	
    	try {
	        response = restService.sendRequest(request, TransactionQueryResponse.class,
	        									url, false, request.getToken());
	
	        if(Status.DECLINED.toString().equals(response.getStatus())){
	            throw new LoginException("Transaction Query Request Declined. Message: " + response.getMessage());
	        }
	
	        if(!Status.APPROVED.toString().equals(response.getStatus()) &&
	                (response.getStatus() != null && !response.getStatus().isEmpty())){
	            throw new SystemException("Transaction Query Request Invocation Received Unexpected Status. Status-Message: "
	                    + response.getStatus() + "-"+ response.getMessage());
	        }
    	}catch(Exception ex) {
    		handleException(ex);
    	}

        return response;
    }
}
