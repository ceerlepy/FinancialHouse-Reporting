package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class TransactionQResponse implements Serializable {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 284468810446648285L;
	
	private  TransactionQueryMerchantResponse transactionMerchantResponse;

    @JsonCreator
    public TransactionQResponse(@JsonProperty("merchant") TransactionQueryMerchantResponse transactionMerchantResponse) {
        this.transactionMerchantResponse = transactionMerchantResponse;
    }
}
