package uk.financial.reporting.transaction.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class MerchantResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -1949686407798921079L;
	
	private String name;

    @JsonCreator
    public MerchantResponse(@JsonProperty("name") String name) {
        this.name = name;
    }
}
