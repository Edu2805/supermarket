package br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto;

import br.com.amorim.supermarket.controller.historicalgoodsreceipt.dto.response.HistoricalGoodsReceiptOutput;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;

import java.util.List;

public interface ConverterHistoricalGoodsReceiptMapper {

    List<HistoricalGoodsReceiptOutput> historicalGoodsReceiptOutputMapper(List<HistoricalGoodsReceipt> historicalGoodsReceiptList);
}
