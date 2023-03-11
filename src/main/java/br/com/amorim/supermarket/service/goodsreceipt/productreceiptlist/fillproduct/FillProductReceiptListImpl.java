package br.com.amorim.supermarket.service.goodsreceipt.productreceiptlist.fillproduct;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FillProductReceiptListImpl implements FillProductReceiptList {

    @Override
    public List<String> fillProducts(GoodsReceipt goodsReceipt) {
        return goodsReceipt.getProductDataList().stream().map(product -> {
            if (product.getDun14() == null) {
                return product.getCode() + " " + product.getEan13() + " " + product.getName() + " " +
                        product.getInventory() + " " + product.getPurchasePrice();
            }
            return product.getCode() + " " + product.getDun14() + " " + product.getName() + " " + product.getInventory() +
                    " " + product.getPurchasePrice();
        }).toList();
    }
}
