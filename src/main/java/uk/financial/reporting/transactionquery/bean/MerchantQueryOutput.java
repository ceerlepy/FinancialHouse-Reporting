package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class MerchantQueryOutput implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3110442533346934429L;
	
	private int id;
    private String name;

    @JsonCreator
    public MerchantQueryOutput(@JsonProperty("id") int id,
    					  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
