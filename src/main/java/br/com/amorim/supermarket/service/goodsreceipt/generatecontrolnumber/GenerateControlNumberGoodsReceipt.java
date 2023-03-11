package br.com.amorim.supermarket.service.goodsreceipt.generatecontrolnumber;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

import java.math.BigInteger;

public interface GenerateControlNumberGoodsReceipt {

    BigInteger generate (GoodsReceipt goodsReceipt);
}
