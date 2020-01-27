package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class TransactionQueryResponse  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6848591699981087034L;
	
	private String status;
    private String message;
    private long perPage;
    private long current_page;
    private String next_page_url;
    private String prev_page_url;
    private long from;
    private long to;
    private Optional<List<DataResponse>> data;

    @JsonCreator
    @Builder
    public TransactionQueryResponse(@JsonProperty("status") String status,
    								@JsonProperty("message") String message,
    								@JsonProperty("per_page") long perPage,
                                    @JsonProperty("current_page") long current_page,
                                    @JsonProperty("next_page_url") String next_page_url,
                                    @JsonProperty("prev_page_url") String prev_page_url,
                                    @JsonProperty("from") long from,
                                    @JsonProperty("to") long to,
                                    @JsonProperty("data") Optional<List<DataResponse>> data) {
        this.status = status;
        this.message = message;
        this.perPage = perPage;
        this.current_page = current_page;
        this.next_page_url = next_page_url;
        this.prev_page_url = prev_page_url;
        this.from = from;
        this.to = to;
        this.data = data;
    }
}
