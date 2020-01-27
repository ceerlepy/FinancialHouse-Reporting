package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AcquirerResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 8848574041032327017L;
	
	private long id;
    private String name;
    private String code;
    private String type;

    @Builder
    @JsonCreator
    public AcquirerResponse(@JsonProperty("id") long id,
    						@JsonProperty("name") String name,
                            @JsonProperty("code") String code,
                            @JsonProperty("type") String type) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
    }
}
