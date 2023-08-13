package br.com.amorim.supermarket.controller.goodsissue.dto.request;

import br.com.amorim.supermarket.common.enums.PaymentOptionsType;
import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoodsIssueDTO {

    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_TOTAL_RECEIVED_IS_NOT_NULL}")
    @Positive(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_TOTAL_RECEIVED_IS_NOT_NEGATIVE}")
    @Digits(integer = 10, fraction = 2, message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_TOTAL_RECEIVED_INCORRECT_FORMAT}")
    private BigDecimal totalReceived;
    private PaymentOptionsType paymentOptionsType;
    @NotNull(message = "{br.com.supermarket.GOODS_ISSUE_DTO_FIELD_PRODUCT_DATA_LIST_IS_NOT_NULL}")
    private List<ProductData> productDataList;
}
