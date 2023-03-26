package br.com.amorim.supermarket.service.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialReportInput;
import br.com.amorim.supermarket.repository.financialstatementreport.FinancialStatementReportRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor

@Service
public class FinancialStatementReportServiceImpl implements FinancialStatementReportService {

    private FinancialStatementReportRepositoryCustom financialStatementReportRepositoryCustom;

    @Override
    public BigDecimal expensiesReport(FinancialReportInput financialReportInput) {
        return financialStatementReportRepositoryCustom.expensiesReportQuery(financialReportInput);
    }
}
