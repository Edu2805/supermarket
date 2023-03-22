package br.com.amorim.supermarket.service.goodsreceipt.totalproduct.fieldsummary;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

import java.math.BigDecimal;

public interface PurchaseSummary {

    BigDecimal calculateTotalProducts(GoodsReceipt goodsReceipt);
    BigDecimal calculateTotalBySubsection(GoodsReceipt goodsReceipt);
    BigDecimal calculateTotalByMainsection(GoodsReceipt goodsReceipt);
    BigDecimal calculateTotalByDepartment(GoodsReceipt goodsReceipt);
}
