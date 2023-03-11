package br.com.amorim.supermarket.controller.goodsreceipt.dto;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;

public interface ConvertGoodsReceiptMapper {

    GoodsReceipt createOrUpdateGoodsReceiptMapper(GoodsReceiptDTO goodsReceiptDTO);
}
