package uk.financial.reporting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.exception.SystemException;
import uk.financial.reporting.login.bean.LoginRequest;
import uk.financial.reporting.login.bean.LoginResponse;
import uk.financial.reporting.service.MerchantLoginService;

@RestController
@RequestMapping(path = "api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MerchantLoginController extends BaseController {

	private MerchantLoginService merchantLoginService;

	@Autowired
	public MerchantLoginController(MerchantLoginService merchantLoginService) {
		this.merchantLoginService = merchantLoginService;
	}

	@PostMapping("merchant/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest)
			throws LoginException, SystemException, RemoteSystemException {

		LoginResponse loginResult = merchantLoginService.login(loginRequest);
		return new ResponseEntity<>(loginResult, HttpStatus.OK);

	}

}
