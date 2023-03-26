package br.com.amorim.supermarket.service.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;
import br.com.amorim.supermarket.repository.financialstatementreport.expensies.FinancialExpensiesStatementReportRepositoryCustom;
import br.com.amorim.supermarket.repository.financialstatementreport.sales.FinancialSalesStatementReportRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor

@Service
public class FinancialStatementReportServiceImpl implements FinancialStatementReportService {

    private FinancialExpensiesStatementReportRepositoryCustom financialStatementReportRepositoryCustom;
    private FinancialSalesStatementReportRepositoryCustom financialSalesStatementReportRepositoryCustom;

    @Override
    public BigDecimal expensiesReport(FinancialExpensiesReportInput financialExpensiesReportInput) {
        return financialStatementReportRepositoryCustom.expensiesReportQuery(financialExpensiesReportInput);
    }

    @Override
    public BigDecimal salesReport(FinancialSalesReportInput financialSalesReportInput) {
        return financialSalesStatementReportRepositoryCustom.expensiesReportQuery(financialSalesReportInput);
    }
}
