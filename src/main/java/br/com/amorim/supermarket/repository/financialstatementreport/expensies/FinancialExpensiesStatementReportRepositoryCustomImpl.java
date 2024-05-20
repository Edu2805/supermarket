package br.com.amorim.supermarket.repository.financialstatementreport.expensies;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.FinancialExpensiesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.request.ExpensiesReportInput;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.QHistoricalGoodsReceipt;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.otheraddition.QOtherAddition;
import br.com.amorim.supermarket.model.salary.QSalary;
import br.com.amorim.supermarket.model.salary.Salary;
import com.querydsl.jpa.impl.JPAQuery;
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

    @Override
    public List<HistoricalGoodsReceipt> expensiesReportQueryList(ExpensiesReportInput expensiesReportInput) {
        Timestamp from;
        Timestamp to;

        if (expensiesReportInput.getFrom() != null && expensiesReportInput.getTo() != null) {
            from = Timestamp.valueOf(expensiesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(expensiesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now().minus(7, java.time.temporal.ChronoUnit.DAYS));
            to = Timestamp.from(Instant.now());
        }

        QHistoricalGoodsReceipt qHistoricalGoodsReceipt = QHistoricalGoodsReceipt.historicalGoodsReceipt;
        JPAQuery<HistoricalGoodsReceipt> query = new JPAQuery<>(entityManager);
        return query.select(qHistoricalGoodsReceipt)
                .from(qHistoricalGoodsReceipt)
                .where(qHistoricalGoodsReceipt.registrationDate.between(from, to)).fetch();
    }

    @Override
    public BigDecimal directExpensiesReportDre(ExpensiesReportInput expensiesReportInput) {
        Timestamp from;
        Timestamp to;

        if (expensiesReportInput.getFrom() != null && expensiesReportInput.getTo() != null) {
            from = Timestamp.valueOf(expensiesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(expensiesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now().minus(30, java.time.temporal.ChronoUnit.DAYS));
            to = Timestamp.from(Instant.now());
        }

        QHistoricalGoodsReceipt qHistoricalGoodsReceipt = QHistoricalGoodsReceipt.historicalGoodsReceipt;
        JPAQuery<HistoricalGoodsReceipt> query = new JPAQuery<>(entityManager);

        var result = query.select(qHistoricalGoodsReceipt.purchasePrice.coalesce(BigDecimal.ZERO)
                        .multiply(qHistoricalGoodsReceipt.inventory.coalesce(BigDecimal.ZERO)).sum())
                .from(qHistoricalGoodsReceipt)
                .where(qHistoricalGoodsReceipt.isReceived.eq(Boolean.TRUE))
                .where(qHistoricalGoodsReceipt.registrationDate.between(from, to))
                .fetchOne();

        return result != null ? result : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal indirectExpensiesReportDre(ExpensiesReportInput expensiesReportInput) {
        Timestamp from;
        Timestamp to;

        if (expensiesReportInput.getFrom() != null && expensiesReportInput.getTo() != null) {
            from = Timestamp.valueOf(expensiesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(expensiesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now().minus(30, java.time.temporal.ChronoUnit.DAYS));
            to = Timestamp.from(Instant.now());
        }

        QOtherAddition qOtherAddition = QOtherAddition.otherAddition;
        JPAQuery<OtherAddition> query = new JPAQuery<>(entityManager);

        var result = query.select(qOtherAddition.additionValue.coalesce(BigDecimal.ZERO).sum())
                .from(qOtherAddition)
                .where(qOtherAddition.salary.competenceStart.eq(from.toLocalDateTime().toLocalDate()))
                .where(qOtherAddition.salary.finalCompetence.eq(to.toLocalDateTime().toLocalDate()))
                .fetchOne();

        return result != null ? result : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal taxesExpensiesReportDre(ExpensiesReportInput expensiesReportInput) {
        Timestamp from;
        Timestamp to;

        if (expensiesReportInput.getFrom() != null && expensiesReportInput.getTo() != null) {
            from = Timestamp.valueOf(expensiesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(expensiesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now().minus(30, java.time.temporal.ChronoUnit.DAYS));
            to = Timestamp.from(Instant.now());
        }

        QSalary qSalary = QSalary.salary;
        JPAQuery<Salary> query = new JPAQuery<>(entityManager);

        var result = query.select(qSalary.fgts.coalesce(BigDecimal.ZERO).sum())
                .from(qSalary)
                .where(qSalary.competenceStart.eq(from.toLocalDateTime().toLocalDate()))
                .where(qSalary.finalCompetence.eq(to.toLocalDateTime().toLocalDate()))
                .fetchOne();

        return result != null ? result : BigDecimal.ZERO;
    }
}