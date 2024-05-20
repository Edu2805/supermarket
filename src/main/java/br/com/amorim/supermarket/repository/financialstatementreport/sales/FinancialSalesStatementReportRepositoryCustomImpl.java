package br.com.amorim.supermarket.repository.financialstatementreport.sales;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.FinancialSalesReportInput;
import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.request.RevenuesReportInput;
import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import br.com.amorim.supermarket.model.historicalgoodsissue.QHistoricalGoodsIssue;
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
public class FinancialSalesStatementReportRepositoryCustomImpl implements FinancialSalesStatementReportRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public BigDecimal salesReportQuery(FinancialSalesReportInput financialSalesReportInput) {
        Timestamp from;
        Timestamp to;

        var providerProductName = financialSalesReportInput.getProviderProductName();
        var departmentName = financialSalesReportInput.getDepartmentName();
        var mainsectionName = financialSalesReportInput.getMainsectionName();
        var subsectionName = financialSalesReportInput.getSubsectionName();
        var productCode = financialSalesReportInput.getProductCode();
        var ean13 = financialSalesReportInput.getEan13();
        var dun14 = financialSalesReportInput.getDun14();
        var saleNumber = financialSalesReportInput.getSaleNumber();
        var isEffectiveSale = financialSalesReportInput.isEffectiveSale();
        if (financialSalesReportInput.getFrom() != null && financialSalesReportInput.getTo() != null) {
            from = Timestamp.valueOf(financialSalesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(financialSalesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now());
            to = Timestamp.from(Instant.now());
        }

        var query = "SELECT SUM(COALESCE(hgi.salePrice, 0) * COALESCE(hgi.inventory, 0)) FROM HistoricalGoodsIssue AS hgi ";
        List<String> conditions = new ArrayList<>();
        if (providerProductName != null && (!providerProductName.isEmpty() || !providerProductName.isBlank())) {
            conditions.add("hgi.providerProductName = :providerProductName");
        }
        if (departmentName != null && (!departmentName.isEmpty() || !departmentName.isBlank())) {
            conditions.add("hgi.departmentName = :departmentName");
        }
        if (mainsectionName != null && (!mainsectionName.isEmpty() || !mainsectionName.isBlank())) {
            conditions.add("hgi.mainsectionName = :mainsectionName");
        }
        if (subsectionName != null && (!subsectionName.isEmpty() || !subsectionName.isBlank())) {
            conditions.add("hgi.subsectionName = :subsectionName");
        }
        if (productCode != null) {
            conditions.add("hgi.productCode = :productCode");
        }
        if (ean13 != null && (!ean13.isEmpty() || !ean13.isBlank())) {
            conditions.add("hgi.ean13 = :ean13");
        }
        if (dun14 != null && (!dun14.isEmpty() || !dun14.isBlank())) {
            conditions.add("hgi.dun14 = :dun14");
        }
        if (saleNumber != null) {
            conditions.add("hgi.saleNumber = :saleNumber");
        }
        conditions.add("hgi.registrationDate BETWEEN :from AND :to");
        conditions.add("hgi.isEffectiveSale = :isEffectiveSale");

        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }

        try {
            var createQuery = entityManager.createQuery(query, BigDecimal.class);
            var bigDecimalTypedQuery = setParameters(createQuery, providerProductName, departmentName, mainsectionName,
                    subsectionName, productCode, ean13, dun14, saleNumber, from, to, isEffectiveSale);

            return bigDecimalTypedQuery.getSingleResult();
        } catch (QueryException qe) {
            throw new QueryException("Query error: " + qe.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    private TypedQuery<BigDecimal> setParameters(TypedQuery<BigDecimal> createQuery, String providerProductName,
                                                 String departmentName, String mainsectionName, String subsectionName,
                                                 BigInteger productCode, String ean13, String dun14, BigInteger saleNumber,
                                                 Timestamp from, Timestamp to, boolean isEffectiveSale) {
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
        if (ean13 != null && (!ean13.isEmpty() || !ean13.isBlank())) {
            createQuery.setParameter("ean13", ean13);
        }
        if (dun14 != null && (!dun14.isEmpty() || !dun14.isBlank())) {
            createQuery.setParameter("dun14", dun14);
        }
        if (saleNumber != null) {
            createQuery.setParameter("saleNumber", saleNumber);
        }
        if (from != null && to != null) {
            createQuery.setParameter("from", from);
            createQuery.setParameter("to", to);
        }
        createQuery.setParameter("isEffectiveSale", isEffectiveSale);

        return createQuery;
    }

    @Override
    public List<HistoricalGoodsIssue> salesReportQueryList(RevenuesReportInput revenuesReportInput) {
        Timestamp from;
        Timestamp to;

        if (revenuesReportInput.getFrom() != null && revenuesReportInput.getTo() != null) {
            from = Timestamp.valueOf(revenuesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(revenuesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now().minus(7, java.time.temporal.ChronoUnit.DAYS));
            to = Timestamp.from(Instant.now());
        }

        QHistoricalGoodsIssue qHistoricalGoodsIssue = QHistoricalGoodsIssue.historicalGoodsIssue;
        JPAQuery<HistoricalGoodsIssue> query = new JPAQuery<>(entityManager);
        return query.select(qHistoricalGoodsIssue)
                .from(qHistoricalGoodsIssue)
                .where(qHistoricalGoodsIssue.registrationDate.between(from, to)).fetch();
    }

    @Override
    public BigDecimal totalRevenues(RevenuesReportInput revenuesReportInput) {
        Timestamp from;
        Timestamp to;

        if (revenuesReportInput.getFrom() != null && revenuesReportInput.getTo() != null) {
            from = Timestamp.valueOf(revenuesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(revenuesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now().minus(30, java.time.temporal.ChronoUnit.DAYS));
            to = Timestamp.from(Instant.now());
        }

        QHistoricalGoodsIssue qHistoricalGoodsIssue = QHistoricalGoodsIssue.historicalGoodsIssue;
        JPAQuery<HistoricalGoodsIssue> query = new JPAQuery<>(entityManager);
        var result = query.select(qHistoricalGoodsIssue.salePrice.coalesce(BigDecimal.ZERO)
                        .multiply(qHistoricalGoodsIssue.inventory.coalesce(BigDecimal.ZERO)).sum())
                .from(qHistoricalGoodsIssue)
                .where(qHistoricalGoodsIssue.isEffectiveSale.eq(Boolean.TRUE))
                .where(qHistoricalGoodsIssue.registrationDate.between(from, to))
                .fetchOne();

        return result != null ? result : BigDecimal.ZERO;
    }

}
