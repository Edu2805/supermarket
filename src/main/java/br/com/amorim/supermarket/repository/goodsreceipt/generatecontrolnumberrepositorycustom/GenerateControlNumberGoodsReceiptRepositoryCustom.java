package br.com.amorim.supermarket.repository.goodsreceipt.generatecontrolnumberrepositorycustom;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

import java.math.BigInteger;

public interface GenerateControlNumberGoodsReceiptRepositoryCustom {

    BigInteger generateRegisterNumber(GoodsReceipt goodsReceipt);
}
