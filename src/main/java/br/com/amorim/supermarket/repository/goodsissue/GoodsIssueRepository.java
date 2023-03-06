package br.com.amorim.supermarket.repository.goodsissue;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GoodsIssueRepository extends JpaRepository<GoodsIssue, UUID> {
}
