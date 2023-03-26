package br.com.amorim.supermarket.controller.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;
import br.com.amorim.supermarket.service.financialstatementreport.FinancialStatementReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor

@RestController
@RequestMapping("api/financial-report")
@Api("Financial Statement Report")
public class FinancialStatementReportController {

    private FinancialStatementReportService financialStatementReportService;

    @GetMapping("/expensies")
    @ApiOperation("Personalized query to fetch total expenses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public BigDecimal getExpensiesFinancialReport(@RequestBody @ApiParam("Parameter structure for search")
                                                      FinancialExpensiesReportInput financialExpensiesReportInput) {
        return financialStatementReportService.expensiesReport(financialExpensiesReportInput);
    }

    @GetMapping("/sales")
    @ApiOperation("Personalized query to fetch total sales")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public BigDecimal getSalesFinancialReport(@RequestBody @ApiParam("Parameter structure for search")
                                                  FinancialSalesReportInput financialSalesReportInput) {
        return financialStatementReportService.salesReport(financialSalesReportInput);
    }
}
