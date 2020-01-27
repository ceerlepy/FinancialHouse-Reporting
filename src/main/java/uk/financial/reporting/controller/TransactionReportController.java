package uk.financial.reporting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.service.TransactionReportService;
import uk.financial.reporting.transactionreport.bean.TransactionReportRequest;
import uk.financial.reporting.transactionreport.bean.TransactionReportResponse;

@RestController
@RequestMapping(value = "api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionReportController extends BaseController {

	private TransactionReportService transactionReportService;

	@Autowired
	public TransactionReportController(TransactionReportService transactionReportService) {
		this.transactionReportService = transactionReportService;
	}

	@PostMapping("transactions/report")
	public ResponseEntity<?> retrieveTransactionReport(@Valid @RequestBody TransactionReportRequest request,
			@RequestHeader("token") String token) 
					throws LoginException, SystemException, RemoteSystemException {

		request.setToken(token);
		TransactionReportResponse transactionReportResponse = transactionReportService.retrieveReportList(request);

		return new ResponseEntity<>(transactionReportResponse, HttpStatus.OK);

	}

}
