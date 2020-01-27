package uk.financial.reporting.login.bean;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -4896524531605464370L;

    @NotBlank(message = "E-Mail is mandatory")
    @JsonProperty("email")
    private String email;
    
    @NotBlank(message = "Password is mandatory")
    @JsonProperty("password")
    private String password;
    
}
