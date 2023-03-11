package br.com.amorim.supermarket.controller.goodsreceipt.dto;

import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoodsReceiptDTO {

    @NotBlank(message = "{br.com.supermarket.GOODS_RECEIPT_DTO_FIELD_INVOICE_IS_NOT_EMPTY}")
    @Size(min = 2, max = 50, message = "{br.com.supermarket.GOODS_RECEIPT_DTO_FIELD_INVOICE_CANNOT_BE_LESS_THAN_2_AND_GREATER_THAN_50}")
    private String invoice;

    @NotNull(message = "{br.com.supermarket.GOODS_RECEIPT_DTO_FIELD_PRODUCT_DATA_LIST_IS_NOT_NULL}")
    private List<ProductData> productDataList;
}
