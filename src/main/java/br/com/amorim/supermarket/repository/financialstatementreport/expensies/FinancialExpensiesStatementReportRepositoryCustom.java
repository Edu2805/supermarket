package br.com.amorim.supermarket.repository.financialstatementreport.expensies;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportInput;

import java.math.BigDecimal;

public interface FinancialExpensiesStatementReportRepositoryCustom {

    BigDecimal expensiesReportQuery(FinancialExpensiesReportInput financialReportInput);
}
