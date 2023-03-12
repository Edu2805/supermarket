package br.com.amorim.supermarket.service.goodsissue.generatesalenumber;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;

import java.math.BigInteger;

public interface GenerateSaleNumber {

    BigInteger generate (GoodsIssue goodsIssue);
}
