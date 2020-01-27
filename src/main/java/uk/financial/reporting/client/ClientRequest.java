package uk.financial.reporting.client;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ClientRequest implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 6092196151820704390L;

	@NotBlank(message = "Transaction id is mandatory")
	@JsonProperty("transactionId")
	private String transactionId;

	@Setter
	@JsonIgnore
	private String token;
}
