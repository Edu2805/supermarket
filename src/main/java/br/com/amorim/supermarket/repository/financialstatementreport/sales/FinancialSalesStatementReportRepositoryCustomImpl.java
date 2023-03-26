package br.com.amorim.supermarket.repository.financialstatementreport.sales;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@AllArgsConstructor

@Repository
public class FinancialSalesStatementReportRepositoryCustomImpl implements FinancialSalesStatementReportRepositoryCustom {

    @Override
    public BigDecimal expensiesReportQuery(FinancialSalesReportInput financialSalesReportInput) {
        return null;
    }
}
