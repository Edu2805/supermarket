package br.com.amorim.supermarket.controller.providerproduct.dto.request;

import br.com.amorim.supermarket.controller.common.dto.CommonDTONameMunicipalAndStateRegistration;
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
public class ProviderProductDTO extends CommonDTONameMunicipalAndStateRegistration {

    @NotNull(message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_SUBSCRIPTION_TYPE_IS_NOT_EMPTY}")
    private String subscriptionType;

    @NotBlank(message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_SUBSCRIPTION_NUMBER_IS_NOT_EMPTY}")
    @Size(min = 11, max = 14, message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_SUBSCRIPTION_NUMBER_CANNOT_BE_LESS_THAN_11_AND_GREATER_THAN_14}")
    private String subscriptionNumber;

    @NotBlank(message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_ADDRESS_IS_NOT_EMPTY}")
    @Size(max = 60, message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_ADDRESS_CANNOT_BE_GREATER_THAN_60}")
    private String address;

    @NotBlank(message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_PHONE_IS_NOT_EMPTY}")
    @Size(min = 8, max = 11, message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_PHONE_CANNOT_BE_LESS_THAN_8_AND_GREATER_THAN_11}")
    private String phone;

    @NotBlank(message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_MANAGER_IS_NOT_EMPTY}")
    @Size(min = 5, max = 60, message = "{br.com.supermarket.PROVIDER_PRODUCT_DTO_FIELD_MANAGER_CANNOT_BE_LESS_THAN_5_AND_GREATER_THAN_60}")
    private String responsible;
}
