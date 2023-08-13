package br.com.amorim.supermarket.controller.goodsissue.dto.response;

import br.com.amorim.supermarket.controller.productdata.dto.response.ProductUnitTypeStringDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GoodsIssuePaymentTypeStringDTO {

    private UUID id;
    private BigInteger saleNumber;
    private BigDecimal productsTotal;
    private BigDecimal subtotal;
    private BigDecimal totalReceived;
    private BigDecimal change;
    private boolean isEffectiveSale;
    private String paymentOptionsType;
    private Timestamp registrationDate;
    private List<ProductUnitTypeStringDTO> productDataList;
    private List<String> productList;
}
