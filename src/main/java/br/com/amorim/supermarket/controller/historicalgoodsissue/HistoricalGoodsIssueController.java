package br.com.amorim.supermarket.controller.historicalgoodsissue;

import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import br.com.amorim.supermarket.service.historicalgoodsissue.HistoricalGoodsIssueCrudService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor

@RestController
@RequestMapping("api/historical-goods-issue")
public class HistoricalGoodsIssueController {

    private HistoricalGoodsIssueCrudService historicalGoodsIssueCrudService;

    @GetMapping
    public Page<HistoricalGoodsIssue> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                      @RequestParam(
                                              value = "size",
                                              required = false,
                                              defaultValue = "20") int size) {
        return historicalGoodsIssueCrudService.getAll(page, size);
    }
}
