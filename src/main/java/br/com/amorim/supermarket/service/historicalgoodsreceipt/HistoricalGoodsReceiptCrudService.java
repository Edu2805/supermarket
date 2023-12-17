package br.com.amorim.supermarket.service.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface HistoricalGoodsReceiptCrudService {

    Page<HistoricalGoodsReceipt> getAll(int page, int size);
    List<HistoricalGoodsReceipt> findByInvoice(String invoice);
    List<HistoricalGoodsReceipt> findBySourceId(UUID sourceid);
}
