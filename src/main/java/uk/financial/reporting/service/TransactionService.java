package uk.financial.reporting.service;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.transaction.bean.TransactionRequest;
import uk.financial.reporting.transaction.bean.TransactionResponse;

public interface TransactionService {

    TransactionResponse retrieveTransaction(TransactionRequest request) throws LoginException, SystemException, RemoteSystemException;
}
