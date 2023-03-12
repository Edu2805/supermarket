package br.com.amorim.supermarket.controller.goodsissue.dto;

import br.com.amorim.supermarket.model.productdata.ProductData;
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
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoodsIssueDTO {

    @NotBlank(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_PRODUCT_DESCRIPTION_IS_NOT_EMPTY}")
    @Size(min = 2, max = 50, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_PRODUCT_DESCRIPTION_BE_LESS_THAN_2_AND_GREATER_THAN_50}")
    private String productDescription;
    @NotBlank(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_BAR_CODE_IS_NOT_EMPTY}")
    @Size(min = 13, max = 14, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_BAR_CODE_CANNOT_BE_LESS_THAN_13_AND_GREATER_THAN_14}")
    private String barCode;
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_UNITY_VALUE_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_UNITY_VALUE_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_UNITY_VALUE_INCORRECT_FORMAT}")
    private BigDecimal unityValue;
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_PRODUCT_TOTAL_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_PRODUCT_TOTAL_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_PRODUCT_TOTAL_INCORRECT_FORMAT}")
    private BigDecimal productTotal;
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_SUBTOTAL_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_SUBTOTAL_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_SUBTOTAL_INCORRECT_FORMAT}")
    private BigDecimal subtotal;
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_TOTAL_RECEIVED_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_TOTAL_RECEIVED_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_TOTAL_RECEIVED_INCORRECT_FORMAT}")
    private BigDecimal totalReceived;
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_CHANGE_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_CHANGE_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_CHANGE_INCORRECT_FORMAT}")
    private BigDecimal change;
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_PRODUCT_DATA_LIST_IS_NOT_NULL}")
    private List<ProductData> productDataList;
}
