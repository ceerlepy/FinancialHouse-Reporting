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
import uk.financial.reporting.service.TransactionService;
import uk.financial.reporting.transaction.bean.TransactionRequest;
import uk.financial.reporting.transaction.bean.TransactionResponse;

@RestController
@RequestMapping(path = "api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController extends BaseController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("transaction")
	public ResponseEntity<?> retrieveTransaction(@Valid @RequestBody TransactionRequest request,
			@RequestHeader("token") String token) 
					throws LoginException, SystemException, RemoteSystemException {

		request.setToken(token);
		TransactionResponse transactionResponse = transactionService.retrieveTransaction(request);

		return new ResponseEntity<>(transactionResponse, HttpStatus.OK);

	}

}
