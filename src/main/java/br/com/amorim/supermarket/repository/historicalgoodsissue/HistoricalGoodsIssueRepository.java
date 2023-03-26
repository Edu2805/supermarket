package br.com.amorim.supermarket.repository.historicalgoodsissue;

import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoricalGoodsIssueRepository extends JpaRepository<HistoricalGoodsIssue, UUID> {
}
