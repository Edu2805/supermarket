package br.com.amorim.supermarket.service.goodsissue.setfieldssalesummary;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.service.goodsissue.setfieldssalesummary.fieldsummary.SalesSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class SetFieldsSaleSummaryImpl implements SetFieldsSaleSummary {

    private SalesSummary salesSummary;

    @Override
    public void setFieldsSummary(GoodsIssue goodsIssue) {
        var totalSaleAndSubTotal = salesSummary.fillTotalSaleAndSubtotal(goodsIssue);
        var calculateReceiveAndChange = salesSummary.calculateTotalReceivedAndChange(goodsIssue);
        goodsIssue.setProductsTotal(totalSaleAndSubTotal);
        goodsIssue.setSubtotal(totalSaleAndSubTotal);
        goodsIssue.setChange(calculateReceiveAndChange);
        goodsIssue.setEffectiveSale(salesSummary.checkOut(goodsIssue));
    }
}
