package uk.financial.reporting.transaction.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class TransactionRequest implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6327152214722557616L;

	@NotBlank(message = "Transaction id is mandatory")
	@JsonProperty("transactionId")
	private String transactionId;

	@Setter
	@JsonIgnore
	private String token;
}
