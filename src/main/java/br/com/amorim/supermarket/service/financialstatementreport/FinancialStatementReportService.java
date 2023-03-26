package br.com.amorim.supermarket.service.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialReportInput;

import java.math.BigDecimal;

public interface FinancialStatementReportService {

    BigDecimal expensiesReport(FinancialReportInput financialReportInput);
}
