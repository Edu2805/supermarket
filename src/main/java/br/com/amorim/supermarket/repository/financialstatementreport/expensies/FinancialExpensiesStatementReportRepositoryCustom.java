package br.com.amorim.supermarket.repository.financialstatementreport.expensies;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.ExpensiesReportInput;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialExpensiesStatementReportRepositoryCustom {

    BigDecimal expensiesReportQuery(FinancialExpensiesReportInput financialReportInput);
    List<HistoricalGoodsReceipt> expensiesReportQueryList(ExpensiesReportInput expensiesReportInput);
    BigDecimal directExpensiesReportDre(ExpensiesReportInput expensiesReportInput);
    BigDecimal indirectExpensiesReportDre(ExpensiesReportInput expensiesReportInput);
    BigDecimal taxesExpensiesReportDre(ExpensiesReportInput expensiesReportInput);
}
