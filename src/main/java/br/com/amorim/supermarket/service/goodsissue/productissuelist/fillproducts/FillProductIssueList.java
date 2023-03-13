package br.com.amorim.supermarket.service.goodsissue.productissuelist.fillproducts;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;

import java.util.List;

public interface FillProductIssueList {

    List<String> fillProducts(GoodsIssue goodsIssue);
}
