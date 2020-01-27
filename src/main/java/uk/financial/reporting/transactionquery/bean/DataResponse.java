package uk.financial.reporting.transactionquery.bean;


import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3366015502229797780L;
	
	private String updated_at;
    private String created_at;
    private Optional<FxResponse> fxResponse;
    private Optional<CustomerInfoQueryResponse> customerInfo;
    private Optional<MerchantQueryOutput> merchant;
    private Optional<IpnResponse> ipn;
    private Optional<TransactionQResponse> transactionResponse;
    private Optional<AcquirerResponse> acquirer;
    private boolean refundable;

    @JsonCreator
    public DataResponse(@JsonProperty("updated_at") String updated_at,
    					@JsonProperty("created_at") String created_at,
    					@JsonProperty("fx") Optional<FxResponse> fxResponse,
    					@JsonProperty("customerInfo") Optional<CustomerInfoQueryResponse> customerInfo,
    					@JsonProperty("merchant") Optional<MerchantQueryOutput> merchant,
    					@JsonProperty("ipn") Optional<IpnResponse> ipn,
    					@JsonProperty("transaction") Optional<TransactionQResponse> transactionResponse,
    					@JsonProperty("acquirer") Optional<AcquirerResponse> acquirer,
    					@JsonProperty("refundable") boolean refundable) {

        this.updated_at = updated_at;
        this.created_at = created_at;
        this.fxResponse = fxResponse;
        this.customerInfo = customerInfo;
        this.merchant = merchant;
        this.transactionResponse = transactionResponse;
        this.acquirer = acquirer;
        this.refundable = refundable;
    }
}
