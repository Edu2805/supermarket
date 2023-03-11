package br.com.amorim.supermarket.service.goodsreceipt.verifyinvoice;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

public interface VerifyInvoice {

    boolean verifyInvoiceInDatabase(GoodsReceipt goodsReceipt);
}
