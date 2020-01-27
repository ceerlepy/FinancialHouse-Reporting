package uk.financial.reporting.client;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ClientCustomerInfoResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4987075425678650992L;
	
	private long id;
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String number;
    private String expiryMonth;
    private String expiryYear;
    private String startMonth;
    private String startYear;
    private String issueNumber;
    private String email;
    private String birthDay;
    private String gender;
    private String billingTitle;
    private String billingFirstName;
    private String billingLastName;
    private String billingCompany;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingPostCode;
    private String billingState;
    private String billingCountry;
    private String billingPhone;
    private String billingFax;
    private String shippingTitle;
    private String shippingFirstName;
    private String shippingLastName;
    private String shippingCompany;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingPostCode;
    private String shippingState;
    private String shippingCountry;
    private String shippingPhone;
    private String shippingFax;
    private String token;

    @JsonCreator
    public ClientCustomerInfoResponse(@JsonProperty("id") long id,  @JsonProperty("created_at") String createDate, @JsonProperty("updated_at") String updateDate,
                                @JsonProperty("deleted_at") String deleteDate, @JsonProperty("number") String number, @JsonProperty("expiryMonth") String expiryMonth,
                                @JsonProperty("expiryYear") String expiryYear, @JsonProperty("startMonth") String startMonth, @JsonProperty("startYear") String startYear,
                                @JsonProperty("issueNumber") String issueNumber, @JsonProperty("email") String email, @JsonProperty("birthday") String birthDay,
                                @JsonProperty("gender") String gender, @JsonProperty("billingTitle") String billingTitle, @JsonProperty("billingFirstName") String billingFirstName,
                                @JsonProperty("billingLastName") String billingLastName, @JsonProperty("billingCompany") String billingCompany, @JsonProperty("billingAddress1") String billingAddress1,
                                @JsonProperty("billingAddress2") String billingAddress2, @JsonProperty("billingCity") String billingCity, @JsonProperty("billingPostCode") String billingPostCode,
                                @JsonProperty("billingState") String billingState, @JsonProperty("billingCountry") String billingCountry,  @JsonProperty("billingPhone") String billingPhone, @JsonProperty("billingFax") String billingFax,
                                @JsonProperty("shippingTitle") String shippingTitle, @JsonProperty("shippingFirstName") String shippingFirstName, @JsonProperty("shippingLastName") String shippingLastName,
                                @JsonProperty("shippingCompany") String shippingCompany, @JsonProperty("shippingAddress1") String shippingAddress1, @JsonProperty("shippingAddress2") String shippingAddress2, @JsonProperty("shippingCity") String shippingCity,
                                @JsonProperty("shippingPostcode") String shippingPostCode,  @JsonProperty("shippingState") String shippingState, @JsonProperty("shippingCountry") String shippingCountry,
                                @JsonProperty("shippingPhone")String shippingPhone, @JsonProperty("shippingFax") String shippingFax, @JsonProperty("token") String token) {
        this.id = id;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
        this.number = number;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.issueNumber = issueNumber;
        this.email = email;
        this.birthDay = birthDay;
        this.gender = gender;
        this.billingTitle = billingTitle;
        this.billingFirstName = billingFirstName;
        this.billingLastName = billingLastName;
        this.billingCompany = billingCompany;
        this.billingAddress1 = billingAddress1;
        this.billingAddress2 = billingAddress2;
        this.billingCity = billingCity;
        this.billingPostCode = billingPostCode;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingPhone = billingPhone;
        this.billingFax = billingFax;
        this.shippingTitle = shippingTitle;
        this.shippingFirstName = shippingFirstName;
        this.shippingLastName = shippingLastName;
        this.shippingCompany = shippingCompany;
        this.shippingAddress1 = shippingAddress1;
        this.shippingAddress2 = shippingAddress2;
        this.shippingCity = shippingCity;
        this.shippingPostCode = shippingPostCode;
        this.shippingState = shippingState;
        this.shippingCountry = shippingCountry;
        this.shippingPhone = shippingPhone;
        this.shippingFax = shippingFax;
        this.token = token;
    }
}
