package br.com.amorim.supermarket.controller.saleinformation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleInformationDTO {

    @Size(min = 7, max = 50, message = "{br.com.supermarket.USER_DATA_DTO_FIELD_USER_NAME_CANNOT_BE_LESS_THAN_7_AND_GREATER_THAN_50}")
    @NotBlank(message = "{br.com.supermarket.USER_DATA_DTO_FIELD_USER_NAME_CANNOT_BE_EMPTY}")
    private String employeeLogin;
}
