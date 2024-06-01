package br.com.amorim.supermarket.controller.userdata.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordChangeDTO {

    @Size(min = 6, max = 8, message = "{br.com.supermarket.USER_DATA_DTO_FIELD_PASSWORD_CANNOT_BE_LESS_THAN_6_AND_GREATER_THAN_8}")
    @NotBlank(message = "{br.com.supermarket.USER_DATA_DTO_FIELD_PASSWORD_CANNOT_BE_EMPTY}")
    private String oldPassword;
    @Size(min = 6, max = 8, message = "{br.com.supermarket.USER_DATA_DTO_FIELD_PASSWORD_CANNOT_BE_LESS_THAN_6_AND_GREATER_THAN_8}")
    @NotBlank(message = "{br.com.supermarket.USER_DATA_DTO_FIELD_PASSWORD_CANNOT_BE_EMPTY}")
    private String newPassword;
}
