package br.com.amorim.supermarket.service.goodsreceipt.totalproduct;

import br.com.amorim.supermarket.model.financialstatement.FinancialStatement;
import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.repository.financialstatement.FinancialStatementRepository;
import br.com.amorim.supermarket.service.goodsreceipt.totalproduct.fieldsummary.PurchaseSummary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@AllArgsConstructor

@Component
public class SetFieldPurchaseSummaryImpl implements SetFieldPurchaseSummary {

    private PurchaseSummary purchaseSummary;
    private FinancialStatementRepository financialStatementRepository;

    @Override
    public void setFieldsSummary(GoodsReceipt goodsReceipt) {
        var totalProducts = purchaseSummary.calculateTotalProducts(goodsReceipt);
        goodsReceipt.setProductsTotal(totalProducts);
    }

    @Override
    public void setFieldsFinancialStatement(GoodsReceipt goodsReceipt) {
        FinancialStatement financialStatement = new FinancialStatement();
        var totalPurchases = purchaseSummary.calculateTotalProducts(goodsReceipt);
        var totalByDepartment = purchaseSummary.calculateTotalByDepartment(goodsReceipt);
        var totalByMainsection = purchaseSummary.calculateTotalByMainsection(goodsReceipt);
        var totalBySubsection = purchaseSummary.calculateTotalBySubsection(goodsReceipt);
        financialStatement.setExpenses(totalPurchases);
        financialStatement.setExpensesByDepartment(totalByDepartment);
        financialStatement.setExpensesByMainSection(totalByMainsection);
        financialStatement.setExpensesBySubSection(totalBySubsection);
        financialStatement.setCompetenceStart(LocalDate.ofInstant(goodsReceipt.getRegistrationDate().toInstant(), ZoneId.systemDefault()));
        financialStatement.setEndCompetence(LocalDate.ofInstant(goodsReceipt.getRegistrationDate().toInstant(), ZoneId.systemDefault()));
        financialStatementRepository.save(financialStatement);
    }
}
