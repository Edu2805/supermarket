package br.com.amorim.supermarket.controller.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response.FinancialExpensiesReportDreOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response.FinancialExpensiesReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.ExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response.HistoricalGoodsReceiptReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.FinancialSalesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response.FinancialRevenuesReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response.FinancialSalesReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.RevenuesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response.HistoricalGoodsIssueReportOutput;
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

import java.util.List;

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

    @PostMapping("/expensies/historical")
    @ApiOperation("Personalized query to fetch a list of historical expenses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public List<HistoricalGoodsReceiptReportOutput> getHistoricalGoodsIssueList(@RequestBody @ApiParam("Parameter structure for search")
                                                                                ExpensiesReportInput expensiesReportInput) {
        return financialStatementReportService.expensiesReportQueryList(expensiesReportInput);
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

    @PostMapping("/sales/historical")
    @ApiOperation("Personalized query to fetch a list of historical sales")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public List<HistoricalGoodsIssueReportOutput> getHistoricalGoodsIssueList(@RequestBody @ApiParam("Parameter structure for search")
                                                                              RevenuesReportInput revenuesReportInput) {
        return financialStatementReportService.salesReportQueryList(revenuesReportInput);
    }

    @PostMapping("/expensies/dre")
    @ApiOperation("Personalized query to fetch total expenses for DRE")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public FinancialExpensiesReportDreOutput getExpensiesFinancialReportDre(@RequestBody @ApiParam("Parameter structure for search")
                                                      ExpensiesReportInput expensiesReportInput) {
        return financialStatementReportService.expensiesReportDre(expensiesReportInput);
    }

    @PostMapping("/revenues/dre")
    @ApiOperation("Personalized query to fetch total sales for DRE")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returned successfully"),
            @ApiResponse(code = 404, message = "Not found for the given parameter")
    })
    public FinancialRevenuesReportOutput getRevenuesReportDre(@RequestBody @ApiParam("Parameter structure for search")
                                                  RevenuesReportInput revenuesReportInput) {
        return financialStatementReportService.revenuesReportDre(revenuesReportInput);
    }
}
