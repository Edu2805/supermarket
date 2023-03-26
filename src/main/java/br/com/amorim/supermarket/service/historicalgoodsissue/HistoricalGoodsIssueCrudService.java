package br.com.amorim.supermarket.service.historicalgoodsissue;

import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import org.springframework.data.domain.Page;

public interface HistoricalGoodsIssueCrudService {

    Page<HistoricalGoodsIssue> getAll(int page, int size);
}
