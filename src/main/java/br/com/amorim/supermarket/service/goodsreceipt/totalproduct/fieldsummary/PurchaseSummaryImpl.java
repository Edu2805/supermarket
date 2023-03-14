package br.com.amorim.supermarket.service.goodsreceipt.totalproduct.fieldsummary;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PurchaseSummaryImpl implements PurchaseSummary {
    @Override
    public BigDecimal calculateTotalProducts(GoodsReceipt goodsReceipt) {
        final BigDecimal[] totalPurchase = {BigDecimal.ZERO};
        goodsReceipt.getProductDataList().forEach(product -> {
            totalPurchase[0] = totalPurchase[0].add(product.getPurchasePrice().multiply(product.getInventory()));
        });
        return totalPurchase[0];
    }
}
