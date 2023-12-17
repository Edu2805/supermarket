package br.com.amorim.supermarket.repository.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoricalGoodsReceiptRepository extends JpaRepository<HistoricalGoodsReceipt, UUID> {

    List<HistoricalGoodsReceipt> findByInvoice(String invoice);
    List<HistoricalGoodsReceipt> findBySourceId(UUID sourceId);
}
