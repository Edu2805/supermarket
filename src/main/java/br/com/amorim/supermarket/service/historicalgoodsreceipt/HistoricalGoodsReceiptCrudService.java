package br.com.amorim.supermarket.service.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;

import java.math.BigDecimal;

public interface HistoricalGoodsReceiptCrudService {

    BigDecimal getTotalResultsQuery(HistoricalGoodsReceipt historicalGoodsReceipt);
}
