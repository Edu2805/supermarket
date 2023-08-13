package br.com.amorim.supermarket.controller.goodsissue.dto.request;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;

public interface ConvertGoodsIssueMapper {

    GoodsIssue createOrUpdateGoodsIssueMapper(GoodsIssueDTO goodsIssueDTO);
}
