package uk.financial.reporting.transactionreport.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionReportResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2682673472657401039L;
	
	private String code;
    private String status;
    private String message;
    private List<Response> responseList;

    @JsonCreator
    @Builder
    public TransactionReportResponse( @JsonProperty("code")String code, @JsonProperty("status") String status,
                                      @JsonProperty("message") String message, @JsonProperty("response") 
    								  @Singular("response") List<Response> responseList) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.responseList = responseList;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Response implements Serializable{

        /**
		 * 
		 */
		private static final long serialVersionUID = 2587364291816288867L;
		
		private long count;
        private long total;
        private String currency;

        @JsonCreator
        @Builder
        public Response(@JsonProperty("count") long count,
        				@JsonProperty("total") long total,
        				@JsonProperty("currency") String currency) {
            this.count = count;
            this.total = total;
            this.currency = currency;
        }
    }


}
