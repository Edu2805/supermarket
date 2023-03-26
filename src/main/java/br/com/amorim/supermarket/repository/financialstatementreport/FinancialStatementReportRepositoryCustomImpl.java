package br.com.amorim.supermarket.repository.financialstatementreport;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.FinancialReportInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;

@AllArgsConstructor

@Repository
public class FinancialStatementReportRepositoryCustomImpl implements FinancialStatementReportRepositoryCustom{

    private EntityManager entityManager;

    @Override
    public BigDecimal expensiesReportQuery(FinancialReportInput financialReportInput) {
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

        var query = "SELECT SUM(hgr.purchasePrice * hgr.inventory) FROM HistoricalGoodsReceipt AS hgr ";
        query += configureWhereClauseForProviderProductName(providerProductName);
        query += configureWhereOrAndClauseForDepartmentName(providerProductName, departmentName);
        query += configureWhereOrAndClauseForMainsectionName(providerProductName, departmentName, mainsectionName);
        query += configureWhereOrAndClauseForSubsectionName(providerProductName, departmentName, mainsectionName, subsectionName);
        query += configureWhereOrAndClauseForProductCode(providerProductName, departmentName, mainsectionName, subsectionName, productCode);
        query += configureWhereOrAndClauseForInvoice(providerProductName, departmentName, mainsectionName, subsectionName, productCode, invoice);
        query += configureWhereOrAndClauseForFromAndTo(providerProductName, departmentName, mainsectionName, subsectionName, productCode, invoice);
        query += configureWhereOrAndClauseForIsReceived(providerProductName, departmentName, mainsectionName, subsectionName, productCode, invoice, from, to);

        var createQuery = entityManager.createQuery(query, BigDecimal.class);
        var bigDecimalTypedQuery = setParameters(createQuery, providerProductName, departmentName, mainsectionName,
                subsectionName, productCode, invoice, from, to, isReceived);

        return bigDecimalTypedQuery.getSingleResult();
    }

    private String configureWhereClauseForProviderProductName(String providerProductName) {
        var query = "";
        if (providerProductName != null && !providerProductName.isEmpty()) {
            query = "WHERE hgr.providerProductName = :providerProductName ";
        }
        return query;
    }

    private String configureWhereOrAndClauseForDepartmentName(String providerProductName, String departmentName) {
        var query = "";
        if (providerProductName == null && departmentName != null && !departmentName.isEmpty()) {
            query = "WHERE hgr.departmentName = :departmentName ";
        } else {
            if (departmentName != null) {
                query = "AND hgr.departmentName = :departmentName ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForMainsectionName(String providerProductName, String departmentName, String mainsectionName) {
        var query = "";
        if ((providerProductName == null && departmentName == null) && mainsectionName != null && !mainsectionName.isEmpty()) {
            query = "WHERE hgr.mainsectionName = :mainsectionName ";
        } else {
            if (mainsectionName != null) {
                query = "AND hgr.mainsectionName = :mainsectionName ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForSubsectionName(String providerProductName, String departmentName,
                                                              String mainsectionName, String subsectionName) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null) && subsectionName != null && !subsectionName.isEmpty()) {
            query = "WHERE hgr.subsectionName = :subsectionName ";
        } else {
            if (subsectionName != null) {
                query = "AND hgr.subsectionName = :subsectionName ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForProductCode(String providerProductName, String departmentName,
                                                           String mainsectionName, String subsectionName, BigInteger productCode) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null) && productCode != null) {
            query = "WHERE hgr.productCode = :productCode ";
        } else {
            if (productCode != null) {
                query = "AND hgr.productCode = :productCode ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForInvoice(String providerProductName, String departmentName,
                                                           String mainsectionName, String subsectionName, BigInteger productCode, String invoice) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null && productCode == null) && invoice != null) {
            query = "WHERE hgr.invoice = :invoice ";
        } else {
            if (productCode != null) {
                query = "AND hgr.invoice = :invoice ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForFromAndTo(String providerProductName, String departmentName,
                                                         String mainsectionName, String subsectionName, BigInteger productCode, String invoice) {
        var query = "";
        if (providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null && productCode == null && invoice == null) {
            query = "WHERE hgr.registrationDate BETWEEN :from AND :to ";
        } else {
            query = "AND hgr.registrationDate BETWEEN :from AND :to ";
        }
        return query;
    }

    private String configureWhereOrAndClauseForIsReceived(String providerProductName, String departmentName,
                                                          String mainsectionName, String subsectionName, BigInteger productCode,
                                                          String invoice, Timestamp from, Timestamp to) {
        var query = "";
        if (providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null && productCode == null && invoice == null && from.toInstant() == null && to.toInstant() == null) {
            query = "WHERE hgr.isReceived = :isReceived";
        } else {
            query = "AND hgr.isReceived = :isReceived";
        }
        return query;
    }

    private TypedQuery<BigDecimal> setParameters(TypedQuery<BigDecimal> createQuery, String providerProductName,
                                                 String departmentName, String mainsectionName, String subsectionName,
                                                 BigInteger productCode, String invoice,
                                                 Timestamp from, Timestamp to, boolean isReceived) {
        if (providerProductName != null) {
            createQuery.setParameter("providerProductName", providerProductName);
        }
        if (departmentName != null) {
            createQuery.setParameter("departmentName", departmentName);
        }
        if (mainsectionName != null) {
            createQuery.setParameter("mainsectionName", mainsectionName);
        }
        if (subsectionName != null) {
            createQuery.setParameter("subsectionName", subsectionName);
        }
        if (productCode != null) {
            createQuery.setParameter("productCode", productCode);
        }
        if (invoice != null) {
            createQuery.setParameter("invoice", invoice);
        }
        if (from.toInstant() != null && to.toInstant() != null) {
            createQuery.setParameter("from", from);
            createQuery.setParameter("to", to);
        }
        createQuery.setParameter("isReceived", isReceived);

        return createQuery;
    }

}