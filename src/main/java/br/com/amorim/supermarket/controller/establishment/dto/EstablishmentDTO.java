package br.com.amorim.supermarket.controller.establishment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstablishmentDTO {

    @NotBlank(message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_NAME_IS_NOT_EMPTY}")
    @Size(min = 5, max = 50, message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_NAME_CANNOT_BE_LESS_THAN_5_AND_GREATER_THAN_50}")
    private String name;

    @Size(min = 14, max = 14, message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_CNPJ_MUST_HAVE_14_CHARACTERS}")
    @NotNull(message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_CNPJ_IS_NOT_EMPTY}")
    @CNPJ(message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_INVALID_CNPJ}")
    private String cnpj;

    @NotBlank(message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_STATE_REGISTRATION_IS_NOT_EMPTY}")
    @Size(max = 20, message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_STATE_REGISTRATION_CANNOT_BE_GREATER_THAN_20}")
    private String stateRegistration;

    @Size(max = 20, message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_MUNICIPAL_REGISTRATION_CANNOT_BE_GREATER_THAN_20}")
    private String municipalRegistration;

    @NotBlank(message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_ADDRESS_IS_NOT_EMPTY}")
    @Size(max = 60, message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_ADDRESS_CANNOT_BE_GREATER_THAN_60}")
    private String address;

    @NotBlank(message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_PHONE_IS_NOT_EMPTY}")
    @Size(min = 8, max = 11, message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_PHONE_CANNOT_BE_LESS_THAN_8_AND_GREATER_THAN_11}")
    private String phone;

    @NotBlank(message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_MANAGER_IS_NOT_EMPTY}")
    @Size(min = 5, max = 60, message = "{br.com.supermarket.ESTABLISHMENT_DTO_FIELD_MANAGER_CANNOT_BE_LESS_THAN_5_AND_GREATER_THAN_60}")
    private String manager;
}
