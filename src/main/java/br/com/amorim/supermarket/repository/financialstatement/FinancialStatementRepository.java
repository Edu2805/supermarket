package br.com.amorim.supermarket.repository.financialstatement;

import br.com.amorim.supermarket.model.financialstatement.FinancialStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FinancialStatementRepository extends JpaRepository<FinancialStatement, UUID> {
}
