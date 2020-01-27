package uk.financial.reporting.service;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.transactionreport.bean.TransactionReportRequest;
import uk.financial.reporting.transactionreport.bean.TransactionReportResponse;

public interface TransactionReportService {

	TransactionReportResponse retrieveReportList(TransactionReportRequest filter) 
    		throws LoginException, SystemException, RemoteSystemException;
}
