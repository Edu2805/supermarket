package br.com.amorim.supermarket.service.goodsreceipt.productreceiptlist;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.service.goodsreceipt.productreceiptlist.fillproduct.FillProductReceiptList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class SetProductListImpl implements SetProductList {

    private FillProductReceiptList fillProductReceiptList;

    @Override
    public void setProduct(GoodsReceipt goodsReceipt) {
        var fillProducts = fillProductReceiptList.fillProducts(goodsReceipt);
        goodsReceipt.setProducReceiptList(fillProducts);
    }
}
