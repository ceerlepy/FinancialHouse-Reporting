package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class TransactionQueryMerchantResponse implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -354724296255922744L;
	
	private String referansNo;
	private String status;
    private String operation;
    private String message;
    private String created_at;
    private String transactionId;

    @JsonCreator
    public TransactionQueryMerchantResponse(@JsonProperty("referansNo") String referansNo,
    								   @JsonProperty("status") String status,
    								   @JsonProperty("operation") String operation,
    								   @JsonProperty("message") String message,
    								   @JsonProperty("created_at") String created_at,
    								   @JsonProperty("transactionId") String transactionId) {
        this.referansNo = referansNo;
        this.status = status;
        this.operation = operation;
        this.message = message;
        this.created_at = created_at;
        this.transactionId = transactionId;
    }
}
