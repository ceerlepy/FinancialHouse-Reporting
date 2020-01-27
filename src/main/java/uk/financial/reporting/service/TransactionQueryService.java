package uk.financial.reporting.service;


import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.transactionquery.bean.TransactionQueryRequest;
import uk.financial.reporting.transactionquery.bean.TransactionQueryResponse;

public interface TransactionQueryService {
    TransactionQueryResponse retrieveTransactions(TransactionQueryRequest transactionQueryRequest) 
    		throws LoginException, SystemException, RemoteSystemException;
}
