package br.com.amorim.supermarket.controller.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import br.com.amorim.supermarket.service.historicalgoodsreceipt.HistoricalGoodsReceiptCrudService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor

@RestController
@RequestMapping("api/historical-goods-receipt")
public class HistoricalGoodsReceiptController {

    private HistoricalGoodsReceiptCrudService historicalGoodsReceiptCrudService;

    @GetMapping
    public BigDecimal getResultsQuery(@RequestBody HistoricalGoodsReceipt historicalGoodsReceipt) {
        return null;
    }
}
