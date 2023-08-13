package br.com.amorim.supermarket.controller.goodsissue.dto.response;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;

public interface ConvertGoodsIssuePaymentTypeStringDTO {

    GoodsIssuePaymentTypeStringDTO mapper(GoodsIssue goodsIssue);
}
