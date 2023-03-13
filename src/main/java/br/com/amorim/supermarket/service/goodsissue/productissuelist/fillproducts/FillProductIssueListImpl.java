package br.com.amorim.supermarket.service.goodsissue.productissuelist.fillproducts;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FillProductIssueListImpl implements FillProductIssueList {

    @Override
    public List<String> fillProducts(GoodsIssue goodsIssue) {
        return goodsIssue.getProductDataList().stream().map(product -> {
            if (product.getDun14() == null) {
                return product.getCode() + " " + product.getEan13() + " " + product.getName() + " " + product.getUnity() +
                        " " + product.getInventory() + " " + product.getSalePrice();
            }
            return product.getCode() + " " + product.getDun14() + " " + product.getName() + " " + product.getUnity() +
                    " " + product.getInventory() + " " + product.getSalePrice();
        }).toList();
    }
}
