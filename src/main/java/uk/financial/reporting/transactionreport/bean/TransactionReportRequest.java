package uk.financial.reporting.transactionreport.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class TransactionReportRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3047560921883910074L;

	
	@NotBlank(message = "Start date is mandatory")
    private String fromDate;
    @NotBlank(message = "Finish date is mandatory")
    private String toDate;
    private String merchant;
    private String acquirer;
    @Setter
    @JsonIgnore
    private String token;

    @JsonCreator
    @Builder
    public TransactionReportRequest(@JsonProperty("fromDate")String fromDate,
    								@JsonProperty("toDate") String toDate,
                                    @JsonProperty("merchant") String merchant,
                                    @JsonProperty("acquirer") String acquirer,
                                    @JsonProperty("token") String token) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.merchant = merchant;
        this.acquirer = acquirer;
        this.token = token;
    }
	

}
