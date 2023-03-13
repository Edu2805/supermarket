package br.com.amorim.supermarket.repository.goodsissue.generatesalenumber;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;

import java.math.BigInteger;

public interface GenerateSaleNumberRepositoryCustom {

    BigInteger generateSaleNumber(GoodsIssue goodsIssue);
}
