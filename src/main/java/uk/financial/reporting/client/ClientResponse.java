package uk.financial.reporting.client;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7248370187353240384L;
	
	@JsonProperty("status")
	private String status;
	
	 @JsonProperty("message")
    private String message;
	 
	@JsonProperty("customerInfo") 
    private ClientCustomerInfoResponse customerInfo;

}
