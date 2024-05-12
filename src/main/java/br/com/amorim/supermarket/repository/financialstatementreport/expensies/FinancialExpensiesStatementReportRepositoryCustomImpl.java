package br.com.amorim.supermarket.repository.financialstatementreport.expensies;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialExpensiesReportInput;
import lombok.AllArgsConstructor;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Repository
public class FinancialExpensiesStatementReportRepositoryCustomImpl implements FinancialExpensiesStatementReportRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public BigDecimal expensiesReportQuery(FinancialExpensiesReportInput financialReportInput) {
        Timestamp from;
        Timestamp to;

        var providerProductName = financialReportInput.getProviderProductName();
        var departmentName = financialReportInput.getDepartmentName();
        var mainsectionName = financialReportInput.getMainsectionName();
        var subsectionName = financialReportInput.getSubsectionName();
        var productCode = financialReportInput.getProductCode();
        var invoice = financialReportInput.getInvoice();
        var isReceived = financialReportInput.isReceived();
        if (financialReportInput.getFrom() != null && financialReportInput.getTo() != null) {
            from = Timestamp.valueOf(financialReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(financialReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now());
            to = Timestamp.from(Instant.now());
        }

        var query = "SELECT SUM(COALESCE(hgr.purchasePrice, 0) * COALESCE(hgr.inventory, 0)) FROM HistoricalGoodsReceipt AS hgr ";
        List<String> conditions = new ArrayList<>();
        if (providerProductName != null && (!providerProductName.isEmpty() || !providerProductName.isBlank())) {
            conditions.add("hgr.providerProductName = :providerProductName");
        }
        if (departmentName != null && (!departmentName.isEmpty() || !departmentName.isBlank())) {
            conditions.add("hgr.departmentName = :departmentName");
        }
        if (mainsectionName != null && (!mainsectionName.isEmpty() || !mainsectionName.isBlank())) {
            conditions.add("hgr.mainsectionName = :mainsectionName");
        }
        if (subsectionName != null && (!subsectionName.isEmpty() || !subsectionName.isBlank())) {
            conditions.add("hgr.subsectionName = :subsectionName");
        }
        if (productCode != null) {
            conditions.add("hgr.productCode = :productCode");
        }
        if (invoice != null && (!invoice.isEmpty() || !invoice.isBlank())) {
            conditions.add("hgr.invoice = :invoice");
        }
        conditions.add("hgr.registrationDate BETWEEN :from AND :to");
        conditions.add("hgr.isReceived = :isReceived");

        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }

        try {
            var createQuery = entityManager.createQuery(query, BigDecimal.class);
            var bigDecimalTypedQuery = setParameters(createQuery, providerProductName, departmentName, mainsectionName,
                    subsectionName, productCode, invoice, from, to, isReceived);

            return bigDecimalTypedQuery.getSingleResult();
        } catch (QueryException qe) {
            throw new QueryException("Query error: " + qe.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private TypedQuery<BigDecimal> setParameters(TypedQuery<BigDecimal> createQuery, String providerProductName,
                                                 String departmentName, String mainsectionName, String subsectionName,
                                                 BigInteger productCode, String invoice,
                                                 Timestamp from, Timestamp to, boolean isReceived) {
        if (providerProductName != null && (!providerProductName.isEmpty() || !providerProductName.isBlank())) {
            createQuery.setParameter("providerProductName", providerProductName);
        }
        if (departmentName != null && (!departmentName.isEmpty() || !departmentName.isBlank())) {
            createQuery.setParameter("departmentName", departmentName);
        }
        if (mainsectionName != null && (!mainsectionName.isEmpty() || !mainsectionName.isBlank())) {
            createQuery.setParameter("mainsectionName", mainsectionName);
        }
        if (subsectionName != null && (!subsectionName.isEmpty() || !subsectionName.isBlank())) {
            createQuery.setParameter("subsectionName", subsectionName);
        }
        if (productCode != null) {
            createQuery.setParameter("productCode", productCode);
        }
        if (invoice != null && (!invoice.isEmpty() || !invoice.isBlank())) {
            createQuery.setParameter("invoice", invoice);
        }
        if (from != null && to != null) {
            createQuery.setParameter("from", from);
            createQuery.setParameter("to", to);
        }
        createQuery.setParameter("isReceived", isReceived);

        return createQuery;
    }

}