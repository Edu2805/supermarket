package br.com.amorim.supermarket.service.goodsreceipt.productreceiptlist.fillproduct;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

import java.util.List;

public interface FillProductReceiptList {

    List<String> fillProducts(GoodsReceipt goodsReceipt);
}
