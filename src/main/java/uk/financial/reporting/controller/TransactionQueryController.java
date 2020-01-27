package uk.financial.reporting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import uk.financial.reporting.service.TransactionQueryService;
import uk.financial.reporting.transactionquery.bean.TransactionQueryRequest;
import uk.financial.reporting.transactionquery.bean.TransactionQueryResponse;

@RestController
@RequestMapping(path = "api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionQueryController extends BaseController {

	private TransactionQueryService transactionQueryService;

	@Autowired
	public TransactionQueryController(TransactionQueryService transactionQueryService) {
		this.transactionQueryService = transactionQueryService;
	}

	@PostMapping("transactions/query")
	public ResponseEntity<?> query(@Valid @RequestBody TransactionQueryRequest request,
			@RequestHeader("token") String token) 
					throws LoginException, SystemException, RemoteSystemException{

		request.setToken(token);
		TransactionQueryResponse response = transactionQueryService.retrieveTransactions(request);

		return ResponseEntity.ok(response);

	}
}
