package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class IpnResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5371558382606267973L;
	
	private boolean received;

    @JsonCreator
    public IpnResponse(@JsonProperty("recevied") boolean recevied) {
        this.received = recevied;
    }
}
