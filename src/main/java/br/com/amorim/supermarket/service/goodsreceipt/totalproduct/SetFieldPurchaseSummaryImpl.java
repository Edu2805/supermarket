package br.com.amorim.supermarket.service.goodsreceipt.totalproduct;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.service.goodsreceipt.totalproduct.fieldsummary.PurchaseSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class SetFieldPurchaseSummaryImpl implements SetFieldPurchaseSummary {

    private PurchaseSummary purchaseSummary;

    @Override
    public void setFieldsSummary(GoodsReceipt goodsReceipt) {
        var totalProducts = purchaseSummary.calculateTotalProducts(goodsReceipt);
        goodsReceipt.setProductsTotal(totalProducts);
    }
}
