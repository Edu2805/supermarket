package br.com.amorim.supermarket.controller.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import br.com.amorim.supermarket.service.historicalgoodsreceipt.HistoricalGoodsReceiptCrudService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("api/historical-goods-receipt")
public class HistoricalGoodsReceiptController {

    private HistoricalGoodsReceiptCrudService historicalGoodsReceiptCrudService;

    @GetMapping
    public Page<HistoricalGoodsReceipt> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                               @RequestParam(
                                                       value = "size",
                                                       required = false,
                                                       defaultValue = "20") int size) {
        return historicalGoodsReceiptCrudService.getAll(page, size);
    }
}
