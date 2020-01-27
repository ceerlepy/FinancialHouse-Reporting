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

import uk.financial.reporting.client.ClientRequest;
import uk.financial.reporting.client.ClientResponse;
import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.service.ClientService;

@RestController
@RequestMapping(path = "api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController extends BaseController {

	private ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping("client")
	public ResponseEntity<?> retrieveClient(@Valid @RequestBody ClientRequest request,
			@RequestHeader("token") String token) throws LoginException, SystemException, RemoteSystemException {

		request.setToken(token);
		ClientResponse transactionResponse = clientService.retrieveClient(request);

		return new ResponseEntity<>(transactionResponse, HttpStatus.OK);

	}

}
