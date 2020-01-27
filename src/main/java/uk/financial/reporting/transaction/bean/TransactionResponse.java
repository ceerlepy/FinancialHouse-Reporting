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
public class TransactionResponse implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = -2669538772808387846L;
	
	private String status;
    private String message;
    private Optional<ExchangeResponse> exchangeResponse;
    private Optional<CustomerInfoResponse> customerInfo;
    private Optional<MerchantResponse> merchant;
    private Optional<TransactionSubResponse> transaction;

    @JsonCreator
    @Builder
    public TransactionResponse(@JsonProperty("status") String status,
    						   @JsonProperty("message") String message,
    						   @JsonProperty("fx") Optional<ExchangeResponse> fx,
                               @JsonProperty("customerInfo") Optional<CustomerInfoResponse> customerInfo,
                               @JsonProperty("merchant") Optional<MerchantResponse> merchant,
                               @JsonProperty("transaction") Optional<TransactionSubResponse> transaction) {
        this.status = status;
        this.message = message;
        this.exchangeResponse = fx;
        this.customerInfo = customerInfo;
        this.merchant = merchant;
        this.transaction = transaction;
    }
}
