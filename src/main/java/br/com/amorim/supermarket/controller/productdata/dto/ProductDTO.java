package br.com.amorim.supermarket.controller.productdata.dto;

import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.subsection.SubSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @NotBlank(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_NAME_IS_NOT_EMPTY}")
    @Size(min = 5, max = 30, message = "Nome deve ter no mínimo 5 e no máximo 30 caracteres.")
    private String name;

    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_UNITY_IS_NOT_NULL}")
    private UnityType unity;

    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_PURCHASE_PRICE_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_PURCHASE_PRICE_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.amorim.spermarket.PRODUCT_DATA_DTO_FIELD_PURCHASE_PRICE_INCORRECT_FORMAT}")
    private BigDecimal purchasePrice;

    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_SALE_PRICE_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_SALE_PRICE_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.amorim.spermarket.PRODUCT_DATA_DTO_FIELD_SALE_PRICE_INCORRECT_FORMAT}")
    private BigDecimal salePrice;

    @Size(min = 13, max = 13, message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_EAN13_MUST_BE_13_DIGITS}")
    private String ean13;

    @Size(min = 14, max = 14, message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_DUN14_MUST_BE_14_DIGITS}")
    private String dun14;

    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_INVENTORY_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_INVENTORY_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_INVENTORY_INCORRECT_FORMAT}")
    private BigDecimal inventory;

    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_SUB_SECTION_IS_NOT_NULL}")
    private SubSection subSection;

    @NotNull(message = "{br.com.supermarket.PRODUCT_DATA_DTO_FIELD_PROVIDER_PRODUCT_IS_NOT_NULL}")
    private ProviderProduct providerProduct;
}
