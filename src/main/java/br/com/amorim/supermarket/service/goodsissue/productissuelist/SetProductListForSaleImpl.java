package br.com.amorim.supermarket.service.goodsissue.productissuelist;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.service.goodsissue.productissuelist.fillproducts.FillProductIssueList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class SetProductListForSaleImpl implements SetProductListForSale {

    private FillProductIssueList fillProductIssueList;

    @Override
    public void fillProducts(GoodsIssue goodsIssue) {
        var fillProducts = fillProductIssueList.fillProducts(goodsIssue);
        goodsIssue.setProductList(fillProducts);
    }
}
