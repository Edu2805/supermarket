package br.com.amorim.supermarket.controller.historicalgoodsissue;

import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import br.com.amorim.supermarket.service.historicalgoodsissue.HistoricalGoodsIssueCrudService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor

@RestController
@RequestMapping("api/historical-goods-issue")
public class HistoricalGoodsIssueController {

    private HistoricalGoodsIssueCrudService historicalGoodsIssueCrudService;

    @GetMapping
    public BigDecimal getResultsQuery(@RequestBody HistoricalGoodsIssue historicalGoodsIssue) {
        return null;
    }
}
