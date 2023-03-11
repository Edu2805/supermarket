package br.com.amorim.supermarket.repository.goodsreceipt.verifyinvoicerepositorycustom;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

public interface VerifyInvoiceRepositoryCustom {

    boolean existsByInvoice(GoodsReceipt goodsReceipt);
}
