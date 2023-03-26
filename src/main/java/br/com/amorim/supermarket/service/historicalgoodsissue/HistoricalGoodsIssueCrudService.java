package br.com.amorim.supermarket.service.historicalgoodsissue;

import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;

import java.math.BigDecimal;

public interface HistoricalGoodsIssueCrudService {

    BigDecimal getTotalResultsQuery(HistoricalGoodsIssue historicalGoodsIssue);
}
