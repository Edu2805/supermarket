package br.com.amorim.supermarket.repository.financialstatementreport.sales;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.FinancialSalesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.RevenuesReportInput;
import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialSalesStatementReportRepositoryCustom {

    BigDecimal salesReportQuery(FinancialSalesReportInput financialSalesReportInput);

    List<HistoricalGoodsIssue> salesReportQueryList(RevenuesReportInput revenuesReportInput);
    BigDecimal totalRevenues(RevenuesReportInput revenuesReportInput);
}
