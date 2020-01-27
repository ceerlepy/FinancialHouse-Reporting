package uk.financial.reporting.transaction.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ExchangeMerchantResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -5600870369044911454L;
	
	private BigDecimal originalAmount;
    private String originalCurrency;

    @JsonCreator
    @Builder
    public ExchangeMerchantResponse(
    		@JsonProperty("originalAmount") BigDecimal originalAmount,
    		@JsonProperty("originalCurrency") String originalCurrency) {
        this.originalAmount = originalAmount;
        this.originalCurrency = originalCurrency;
    }
}
