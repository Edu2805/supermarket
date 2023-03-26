package br.com.amorim.supermarket.controller.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;
import br.com.amorim.supermarket.service.financialstatementreport.FinancialStatementReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor

@RestController
@RequestMapping("api/financial-report")
public class FinancialStatementReportController {

    private FinancialStatementReportService financialStatementReportService;

    @GetMapping("/expensies")
    public BigDecimal getExpensiesFinancialReport(@RequestBody FinancialExpensiesReportInput financialExpensiesReportInput) {
        return financialStatementReportService.expensiesReport(financialExpensiesReportInput);
    }

    @GetMapping("/sales")
    public BigDecimal getSalesFinancialReport(@RequestBody FinancialSalesReportInput financialSalesReportInput) {
        return financialStatementReportService.salesReport(financialSalesReportInput);
    }
}
