package br.com.amorim.supermarket.repository.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialReportInput;

import java.math.BigDecimal;

public interface FinancialStatementReportRepositoryCustom {

    BigDecimal expensiesReportQuery(FinancialReportInput financialReportInput);
}
