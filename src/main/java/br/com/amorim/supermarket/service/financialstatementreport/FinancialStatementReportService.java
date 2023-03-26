package br.com.amorim.supermarket.service.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;

import java.math.BigDecimal;

public interface FinancialStatementReportService {

    BigDecimal expensiesReport(FinancialExpensiesReportInput financialExpensiesReportInput);
    BigDecimal salesReport(FinancialSalesReportInput financialSalesReportInput);
}
