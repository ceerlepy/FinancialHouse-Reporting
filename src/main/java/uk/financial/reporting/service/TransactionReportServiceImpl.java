package uk.financial.reporting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import uk.financial.reporting.common.Statics.Status;
import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.rest.RestService;
import uk.financial.reporting.transactionreport.bean.TransactionReportRequest;
import uk.financial.reporting.transactionreport.bean.TransactionReportResponse;

@Service
public class TransactionReportServiceImpl extends BaseService implements TransactionReportService {

	private RestService<TransactionReportRequest, TransactionReportResponse> restService;
	private String url;
	
	@Autowired
	public TransactionReportServiceImpl(
			RestService<TransactionReportRequest, TransactionReportResponse> restService,
			@Value("${reporting.api.transaction.report.url}") String url) {
		this.restService = restService;
		this.url = url;
	}

	@Override
	public TransactionReportResponse retrieveReportList(TransactionReportRequest request)
			throws LoginException, SystemException, RemoteSystemException {

		TransactionReportResponse transactionReportResponse = null;
		
		try {
			transactionReportResponse = restService.sendRequest(request,
					TransactionReportResponse.class, url, false, request.getToken());
	
			if (Status.DECLINED.equals(transactionReportResponse.getStatus())) {
				
				throw new LoginException(transactionReportResponse.getStatus());
			}
	
			if (!Status.APPROVED.equals(transactionReportResponse.getStatus())
					&& (transactionReportResponse.getStatus() != null
							&& !transactionReportResponse.getStatus().isEmpty())) {
				throw new SystemException("Transaction Report Invocation Received Unexpected Status. Status-Message: "
						+ transactionReportResponse.getStatus() + "-" + transactionReportResponse.getMessage());
			}
		}catch(Exception ex) {
			handleException(ex);
    	}
		
		return transactionReportResponse;
	}

}
