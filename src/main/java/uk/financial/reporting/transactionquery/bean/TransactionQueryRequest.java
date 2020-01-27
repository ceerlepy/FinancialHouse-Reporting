package uk.financial.reporting.transactionquery.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class TransactionQueryRequest implements Serializable {

    private static final long serialVersionUID = 6602409492731499646L;

    @NotBlank(message = "fromDate is mandatory")
    @JsonProperty("fromDate")
    private String fromDate;

    @NotBlank(message = "toDate is mandatory")
    @JsonProperty("toDate")
    private String toDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("operation")
    private List<String> operation;

    @JsonProperty("merchantId")
    private String merchantId;

    @JsonProperty("acquirerId")
    private String acquirerId;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("filterField")
    private String filterField;

    @JsonProperty("filterValue")
    private String filterValue;

    @JsonProperty("page")
    private String page;

    @Setter
    @JsonIgnore
    private String token;
}
