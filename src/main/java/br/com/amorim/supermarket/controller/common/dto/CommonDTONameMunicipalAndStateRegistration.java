package br.com.amorim.supermarket.controller.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter

public abstract class CommonDTONameMunicipalAndStateRegistration {

    @NotBlank(message = "{br.com.supermarket.COMMON_DTO_FIELD_NAME_IS_NOT_EMPTY}")
    @Size(min = 5, max = 50, message = "{br.com.supermarket.COMMON_DTO_FIELD_NAME_CANNOT_BE_LESS_THAN_5_AND_GREATER_THAN_50}")
    private String name;

    @NotBlank(message = "{br.com.supermarket.COMMON_DTO_FIELD_STATE_REGISTRATION_IS_NOT_EMPTY}")
    @Size(max = 20, message = "{br.com.supermarket.COMMON_DTO_FIELD_STATE_REGISTRATION_CANNOT_BE_GREATER_THAN_20}")
    private String stateRegistration;

    @Size(max = 20, message = "{br.com.supermarket.COMMON_DTO_FIELD_MUNICIPAL_REGISTRATION_CANNOT_BE_GREATER_THAN_20}")
    private String municipalRegistration;
}
