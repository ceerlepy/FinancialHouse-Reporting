package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class MerchantQueryResponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -205512744917392268L;
	
	private int originalAmount;
    private String originalCurrency;

    @JsonCreator
    public MerchantQueryResponse(@JsonProperty("originalAmount") int originalAmount,
    						@JsonProperty("originalCurrency") String originalCurrency) {
        this.originalAmount = originalAmount;
        this.originalCurrency = originalCurrency;
    }
}
