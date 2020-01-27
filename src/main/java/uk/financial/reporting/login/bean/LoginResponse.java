package uk.financial.reporting.login.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = -7074736170947720416L;

    private String token;
    private String status;
    private String message;

    @Builder
    @JsonCreator
    public LoginResponse(@JsonProperty("token")String token,  @JsonProperty("status")String status, @JsonProperty("message")String message) {
        this.token = token;
        this.status = status;
        this.message = message;
    }
}
