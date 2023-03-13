package br.com.amorim.supermarket.service.goodsissue.setfieldssalesummary.fieldsummary;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;

import java.math.BigDecimal;

public interface SalesSummary {

    BigDecimal fillTotalSaleAndSubtotal(GoodsIssue goodsIssue);
    BigDecimal calculateTotalReceivedAndChange(GoodsIssue goodsIssue);

    boolean checkOut(GoodsIssue goodsIssue);
}
