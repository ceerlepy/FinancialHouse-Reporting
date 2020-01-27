package uk.financial.reporting.transaction.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class AgentResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5398344895711907012L;
	
	private long id;
    private String customerIp;
    private String customerUserAgent;
    private String merchantIp;
    private String merchantUserAgent;
    private String created_at;
    private String updated_at;
    private String deleted_at;

    @JsonCreator
    public AgentResponse(@JsonProperty("id") long id, @JsonProperty("customerIp") String customerIp, @JsonProperty("customerUserAgent") String customerUserAgent,
                         @JsonProperty("merchantIp") String merchantIp, @JsonProperty("merchantUserAgent") String merchantUserAgent, @JsonProperty("created_at") String created_at,
                         @JsonProperty("updated_at") String updated_at, @JsonProperty("deleted_at") String deleted_at) {
        this.id = id;
        this.customerIp = customerIp;
        this.customerUserAgent = customerUserAgent;
        this.merchantIp = merchantIp;
        this.merchantUserAgent = merchantUserAgent;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
    }
}
