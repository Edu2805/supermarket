package br.com.amorim.supermarket.service.historicalgoodsissue;

import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor

@Service
public class HistoricalGoodsIssueCrudServiceImpl implements HistoricalGoodsIssueCrudService {
    @Override
    public BigDecimal getTotalResultsQuery(HistoricalGoodsIssue historicalGoodsIssue) {
        return null;
    }
}
