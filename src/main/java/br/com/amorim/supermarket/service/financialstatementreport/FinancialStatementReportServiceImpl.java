package br.com.amorim.supermarket.service.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.ExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response.FinancialExpensiesReportDreOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response.HistoricalGoodsReceiptReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.FinancialSalesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.RevenuesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response.FinancialRevenuesReportOutput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response.HistoricalGoodsIssueReportOutput;
import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import br.com.amorim.supermarket.repository.financialstatementreport.expensies.FinancialExpensiesStatementReportRepositoryCustom;
import br.com.amorim.supermarket.repository.financialstatementreport.sales.FinancialSalesStatementReportRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<HistoricalGoodsReceiptReportOutput> expensiesReportQueryList(ExpensiesReportInput expensiesReportInput) {
        var outputs = new ArrayList<HistoricalGoodsReceiptReportOutput>();
        List<HistoricalGoodsReceipt> historicalGoodsReceipts = financialStatementReportRepositoryCustom.expensiesReportQueryList(expensiesReportInput);
        historicalGoodsReceipts.forEach(historicalGoodsReceipt -> {
            var output = new HistoricalGoodsReceiptReportOutput();
            output.setName(historicalGoodsReceipt.getName());
            output.setProductCode(historicalGoodsReceipt.getProductCode());
            output.setInventory(historicalGoodsReceipt.getInventory());
            output.setProviderProductName(historicalGoodsReceipt.getProviderProductName());
            output.setDepartmentName(historicalGoodsReceipt.getDepartmentName());
            output.setMainsectionName(historicalGoodsReceipt.getMainsectionName());
            output.setSubsectionName(historicalGoodsReceipt.getSubsectionName());
            output.setInvoiceNumber(historicalGoodsReceipt.getInvoice());
            output.setTotalInvoice(historicalGoodsReceipt.getTotalInvoice());
            output.setRegistrationDate(historicalGoodsReceipt.getRegistrationDate());
            output.setReceived(historicalGoodsReceipt.isReceived());
            outputs.add(output);
        });
        return outputs;
    }

    @Override
    public List<HistoricalGoodsIssueReportOutput> salesReportQueryList(RevenuesReportInput revenuesReportInput) {
        var outputs = new ArrayList<HistoricalGoodsIssueReportOutput>();
        List<HistoricalGoodsIssue> historicalGoodsIssues = financialSalesStatementReportRepositoryCustom.salesReportQueryList(revenuesReportInput);
        historicalGoodsIssues.forEach(historicalGoodsIssue -> {
            var output = new HistoricalGoodsIssueReportOutput();
            output.setName(historicalGoodsIssue.getName());
            output.setProductCode(historicalGoodsIssue.getProductCode());
            output.setEan13(historicalGoodsIssue.getEan13());
            output.setDun14(historicalGoodsIssue.getDun14());
            output.setSalePrice(historicalGoodsIssue.getSalePrice());
            output.setInventory(historicalGoodsIssue.getInventory());
            output.setProviderProductName(historicalGoodsIssue.getProviderProductName());
            output.setDepartmentName(historicalGoodsIssue.getDepartmentName());
            output.setMainsectionName(historicalGoodsIssue.getMainsectionName());
            output.setSubsectionName(historicalGoodsIssue.getSubsectionName());
            output.setSaleNumber(historicalGoodsIssue.getSaleNumber());
            output.setProductsTotal(historicalGoodsIssue.getProductsTotal());
            output.setEffectiveSale(historicalGoodsIssue.isEffectiveSale());
            output.setRegistrationDate(historicalGoodsIssue.getRegistrationDate());
            outputs.add(output);
        });
        return outputs;
    }

    @Override
    public FinancialExpensiesReportDreOutput expensiesReportDre(ExpensiesReportInput expensiesReportInput) {
        var directExpensiesReportDre = financialStatementReportRepositoryCustom.directExpensiesReportDre(expensiesReportInput);
        var indirectExpensiesReportDre = financialStatementReportRepositoryCustom.indirectExpensiesReportDre(expensiesReportInput);
        var taxesExpensiesReportDre = financialStatementReportRepositoryCustom.taxesExpensiesReportDre(expensiesReportInput);
        var totalExpensies = directExpensiesReportDre.add(indirectExpensiesReportDre).add(taxesExpensiesReportDre);
        return new FinancialExpensiesReportDreOutput(directExpensiesReportDre, indirectExpensiesReportDre, taxesExpensiesReportDre, totalExpensies);
    }

    @Override
    public FinancialRevenuesReportOutput revenuesReportDre(RevenuesReportInput revenuesReportInput) {
        var salesReportQuery = financialSalesStatementReportRepositoryCustom.totalRevenues(revenuesReportInput);
        return new FinancialRevenuesReportOutput(salesReportQuery);
    }
}
