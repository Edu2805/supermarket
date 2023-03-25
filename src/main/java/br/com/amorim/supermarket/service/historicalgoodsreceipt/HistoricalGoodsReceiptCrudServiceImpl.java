package br.com.amorim.supermarket.service.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor

@Service
public class HistoricalGoodsReceiptCrudServiceImpl implements HistoricalGoodsReceiptCrudService {


    @Override
    public BigDecimal getTotalResultsQuery(HistoricalGoodsReceipt historicalGoodsReceipt) {
        return null;
    }
}
