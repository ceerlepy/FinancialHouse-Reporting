package uk.financial.reporting.transaction.bean;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionMerchantResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4170230280762948432L;
	
	private String referenceNo;
    private long merchantId;
    private String status;
    private String channel;
    private String channelData;
    private String chainId;
    private String type;
    private long agentInfoId;
    private String operation;
    private String updated_at;
    private String created_at;
    private long id;
    private long fxTransactionId;
    private long acquirerTransactionId;
    private String code;
    private String message;
    private String transactionId;
    private Optional<AgentResponse> agent;

    @JsonCreator
    public TransactionMerchantResponse(@JsonProperty("referenceNo") String referenceNo, @JsonProperty("merchantId") long merchantId, @JsonProperty("status") String status,
                                       @JsonProperty("channel") String channel, @JsonProperty("channelData") String channelData, @JsonProperty("chainId") String chainId,
                                       @JsonProperty("type") String type, @JsonProperty("agentInfoId") long agentInfoId, @JsonProperty("operation") String operation,
                                       @JsonProperty("updated_at") String updated_at, @JsonProperty("created_at") String created_at, @JsonProperty("id") long id, @JsonProperty("fxTransactionId") long fxTransactionId,
                                       @JsonProperty("acquirerTransactionId") long acquirerTransactionId, @JsonProperty("code") String code, @JsonProperty("message") String message, @JsonProperty("transactionId") String transactionId, @JsonProperty("agent") Optional<AgentResponse> agent) {
        this.referenceNo = referenceNo;
        this.merchantId = merchantId;
        this.status = status;
        this.channel = channel;
        this.channelData = channelData;
        this.chainId = chainId;
        this.type = type;
        this.agentInfoId = agentInfoId;
        this.operation = operation;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.id = id;
        this.fxTransactionId = fxTransactionId;
        this.acquirerTransactionId = acquirerTransactionId;
        this.code = code;
        this.message = message;
        this.transactionId = transactionId;
        this.agent = agent;
    }
}
