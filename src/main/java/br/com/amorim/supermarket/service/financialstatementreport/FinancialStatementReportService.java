package br.com.amorim.supermarket.service.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.ExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response.FinancialExpensiesReportDreOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response.HistoricalGoodsReceiptReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.FinancialSalesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.RevenuesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response.FinancialRevenuesReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response.HistoricalGoodsIssueReportOutput;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialStatementReportService {

    BigDecimal expensiesReport(FinancialExpensiesReportInput financialExpensiesReportInput);
    BigDecimal salesReport(FinancialSalesReportInput financialSalesReportInput);
    List<HistoricalGoodsReceiptReportOutput> expensiesReportQueryList(ExpensiesReportInput expensiesReportInput);
    List<HistoricalGoodsIssueReportOutput> salesReportQueryList(RevenuesReportInput revenuesReportInput);
    FinancialExpensiesReportDreOutput expensiesReportDre(ExpensiesReportInput expensiesReportInput);
    FinancialRevenuesReportOutput revenuesReportDre(RevenuesReportInput revenuesReportInput);
}
