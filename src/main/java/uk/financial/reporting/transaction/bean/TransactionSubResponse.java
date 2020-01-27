package uk.financial.reporting.transaction.bean;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionSubResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2582251507976976571L;
	
	private Optional<TransactionMerchantResponse> merchant;

    @JsonCreator
    public TransactionSubResponse(@JsonProperty("merchant")  Optional<TransactionMerchantResponse> merchant) {
        this.merchant = merchant;
    }
}
