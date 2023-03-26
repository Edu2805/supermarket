package br.com.amorim.supermarket.controller.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialReportInput;
import br.com.amorim.supermarket.service.financialstatementreport.FinancialStatementReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("api/financial-report")
public class FinancialStatementReportController {

    private FinancialStatementReportService financialStatementReportService;

    @GetMapping
    public BigDecimal getFinancialReport(@RequestBody FinancialReportInput financialReportInput) {
        return financialStatementReportService.expensiesReport(financialReportInput);
    }
}
