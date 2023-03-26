package br.com.amorim.supermarket.repository.financialstatementreport.sales;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;

import java.math.BigDecimal;

public interface FinancialSalesStatementReportRepositoryCustom {

    BigDecimal expensiesReportQuery(FinancialSalesReportInput financialSalesReportInput);
}
