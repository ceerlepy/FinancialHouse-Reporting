package uk.financial.reporting.transaction.bean;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 3235164869708377534L;

	public static final ExchangeResponse NULL = null;

    private Optional<ExchangeMerchantResponse> merchant;

    @JsonCreator
    @Builder
    public ExchangeResponse(@JsonProperty("merchant") Optional<ExchangeMerchantResponse> merchant) {
        this.merchant = merchant;
    }
}
