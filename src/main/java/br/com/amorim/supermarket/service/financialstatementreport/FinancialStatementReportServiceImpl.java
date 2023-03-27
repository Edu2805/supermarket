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
    private static final int VALUE_ZERO = 0;

    @Override
    public BigDecimal expensiesReport(FinancialExpensiesReportInput financialExpensiesReportInput) {
        var result = financialStatementReportRepositoryCustom.expensiesReportQuery(financialExpensiesReportInput);
        if (result == null) {
            return BigDecimal.valueOf(VALUE_ZERO);
        }
        return result;
    }

    @Override
    public BigDecimal salesReport(FinancialSalesReportInput financialSalesReportInput) {
        var result = financialSalesStatementReportRepositoryCustom.salesReportQuery(financialSalesReportInput);
        if (result == null) {
            return BigDecimal.valueOf(VALUE_ZERO);
        }
        return result;
    }
}
