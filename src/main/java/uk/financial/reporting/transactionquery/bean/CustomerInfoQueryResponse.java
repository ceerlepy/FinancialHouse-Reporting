package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class CustomerInfoQueryResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 9049958876860205797L;
	
	private String number;
    private String email;
    private String billingFirstName;
    private String billingLastName;

    @JsonCreator
    public CustomerInfoQueryResponse(@JsonProperty("number") String number,
    							@JsonProperty("email") String email,
                                @JsonProperty("billingFirstName") String billingFirstName,
                                @JsonProperty("billingLastName") String billingLastName) {
        this.number = number;
        this.email = email;
        this.billingFirstName = billingFirstName;
        this.billingLastName = billingLastName;
    }
}
