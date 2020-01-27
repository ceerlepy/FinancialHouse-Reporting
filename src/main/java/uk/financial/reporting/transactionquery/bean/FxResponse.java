package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class FxResponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 727464371681396248L;
	
	private MerchantQueryResponse merchantResponse;

    @JsonCreator
    public FxResponse(@JsonProperty("merchant") MerchantQueryResponse merchantResponse) {
        this.merchantResponse = merchantResponse;
    }
}
