package br.com.amorim.supermarket.controller.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportOutput;
import br.com.amorim.supermarket.service.financialstatementreport.FinancialStatementReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("api/financial-report")
@Api("Financial Statement Report")
public class FinancialStatementReportController {

    private FinancialStatementReportService financialStatementReportService;

    @PostMapping("/expensies")
    @ApiOperation("Personalized query to fetch total expenses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public FinancialExpensiesReportOutput getExpensiesFinancialReport(@RequestBody @ApiParam("Parameter structure for search")
                                                      FinancialExpensiesReportInput financialExpensiesReportInput) {
        var expensiesReport = financialStatementReportService.expensiesReport(financialExpensiesReportInput);
        return new FinancialExpensiesReportOutput(expensiesReport);
    }

    @PostMapping("/sales")
    @ApiOperation("Personalized query to fetch total sales")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public FinancialSalesReportOutput getSalesFinancialReport(@RequestBody @ApiParam("Parameter structure for search")
                                                  FinancialSalesReportInput financialSalesReportInput) {
        var salesReport = financialStatementReportService.salesReport(financialSalesReportInput);
        return new FinancialSalesReportOutput(salesReport);
    }
}
