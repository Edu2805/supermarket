package br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CredentialsDTORoleString {

    @Size(min = 7, max = 50, message = "{br.com.supermarket.USER_DATA_DTO_FIELD_USER_NAME_CANNOT_BE_LESS_THAN_7_AND_GREATER_THAN_50}")
    @NotBlank(message = "{br.com.supermarket.USER_DATA_DTO_FIELD_USER_NAME_CANNOT_BE_EMPTY}")
    private String login;
    @Size(min = 6, max = 8, message = "{br.com.supermarket.USER_DATA_DTO_FIELD_PASSWORD_CANNOT_BE_LESS_THAN_6_AND_GREATER_THAN_8}")
    @NotBlank(message = "{br.com.supermarket.USER_DATA_DTO_FIELD_PASSWORD_CANNOT_BE_EMPTY}")
    private String password;
    @NotNull(message = "{br.com.supermarket.USER_DATA_FIELD_ROLE_IS_NOT_EMPTY}")
    private String role;
}
