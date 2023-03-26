package br.com.amorim.supermarket.service.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import org.springframework.data.domain.Page;

public interface HistoricalGoodsReceiptCrudService {

    Page<HistoricalGoodsReceipt> getAll(int page, int size);
}
