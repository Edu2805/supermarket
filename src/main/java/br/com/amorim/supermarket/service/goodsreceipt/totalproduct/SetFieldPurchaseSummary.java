package br.com.amorim.supermarket.service.goodsreceipt.totalproduct;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

public interface SetFieldPurchaseSummary {

    void calculateTotalProducts(GoodsReceipt goodsReceipt);
    void setFieldsHistoricalGoodsReceipt(GoodsReceipt goodsReceipt);
}
