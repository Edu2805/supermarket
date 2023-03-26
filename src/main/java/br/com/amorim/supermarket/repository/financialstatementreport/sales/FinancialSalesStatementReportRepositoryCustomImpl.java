package br.com.amorim.supermarket.repository.financialstatementreport.sales;

import br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.FinancialSalesReportInput;
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
        var saleNumber = financialSalesReportInput.getSaleNumber();
        var ean13 = financialSalesReportInput.getEan13();
        var dun14 = financialSalesReportInput.getDun14();
        var isEffectiveSale = financialSalesReportInput.isEffectiveSale();
        if (financialSalesReportInput.getFrom() != null && financialSalesReportInput.getTo() != null) {
            from = Timestamp.valueOf(financialSalesReportInput.getFrom().atStartOfDay());
            to = Timestamp.valueOf(financialSalesReportInput.getTo().atStartOfDay());
        } else {
            from = Timestamp.from(Instant.now());
            to = Timestamp.from(Instant.now());
        }

        var query = "SELECT SUM(hgi.salePrice * hgi.inventory) FROM HistoricalGoodsIssue AS hgi ";
        query += configureWhereClauseForProviderProductName(providerProductName);
        query += configureWhereOrAndClauseForDepartmentName(providerProductName, departmentName);
        query += configureWhereOrAndClauseForMainsectionName(providerProductName, departmentName, mainsectionName);
        query += configureWhereOrAndClauseForSubsectionName(providerProductName, departmentName, mainsectionName, subsectionName);
        query += configureWhereOrAndClauseForProductCode(providerProductName, departmentName, mainsectionName, subsectionName, productCode);
        query += configureWhereOrAndClauseForEan13(providerProductName, departmentName, mainsectionName, subsectionName, productCode, saleNumber, ean13);
        query += configureWhereOrAndClauseForDun14(providerProductName, departmentName, mainsectionName, subsectionName, productCode, saleNumber, ean13, dun14);
        query += configureWhereOrAndClauseForSaleNumber(providerProductName, departmentName, mainsectionName, subsectionName, productCode, saleNumber);
        query += configureWhereOrAndClauseForFromAndTo(providerProductName, departmentName, mainsectionName, subsectionName, productCode, ean13, dun14, saleNumber);
        query += configureWhereOrAndClauseForIsEffectiveSale(providerProductName, departmentName, mainsectionName, subsectionName, productCode, ean13, dun14, saleNumber, from, to);

        var createQuery = entityManager.createQuery(query, BigDecimal.class);
        var bigDecimalTypedQuery = setParameters(createQuery, providerProductName, departmentName, mainsectionName,
                subsectionName, productCode, ean13, dun14, saleNumber, from, to, isEffectiveSale);

        return bigDecimalTypedQuery.getSingleResult();
    }

    private String configureWhereClauseForProviderProductName(String providerProductName) {
        var query = "";
        if (providerProductName != null && !providerProductName.isEmpty()) {
            query = "WHERE hgi.providerProductName = :providerProductName ";
        }
        return query;
    }

    private String configureWhereOrAndClauseForDepartmentName(String providerProductName, String departmentName) {
        var query = "";
        if (providerProductName == null && departmentName != null && !departmentName.isEmpty()) {
            query = "WHERE hgi.departmentName = :departmentName ";
        } else {
            if (departmentName != null) {
                query = "AND hgi.departmentName = :departmentName ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForMainsectionName(String providerProductName, String departmentName, String mainsectionName) {
        var query = "";
        if ((providerProductName == null && departmentName == null) && mainsectionName != null && !mainsectionName.isEmpty()) {
            query = "WHERE hgi.mainsectionName = :mainsectionName ";
        } else {
            if (mainsectionName != null) {
                query = "AND hgi.mainsectionName = :mainsectionName ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForSubsectionName(String providerProductName, String departmentName,
                                                              String mainsectionName, String subsectionName) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null) && subsectionName != null && !subsectionName.isEmpty()) {
            query = "WHERE hgi.subsectionName = :subsectionName ";
        } else {
            if (subsectionName != null) {
                query = "AND hgi.subsectionName = :subsectionName ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForProductCode(String providerProductName, String departmentName,
                                                           String mainsectionName, String subsectionName, BigInteger productCode) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null) && productCode != null) {
            query = "WHERE hgi.productCode = :productCode ";
        } else {
            if (productCode != null) {
                query = "AND hgi.productCode = :productCode ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForEan13(String providerProductName, String departmentName,
                                                     String mainsectionName, String subsectionName, BigInteger productCode,
                                                     BigInteger saleNumber, String ean13) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null
                && productCode == null && saleNumber == null) && ean13 != null) {
            query = "WHERE hgi.ean13 = :ean13 ";
        } else {
            if (ean13 != null) {
                query = "AND hgi.ean13 = :ean13 ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForDun14(String providerProductName, String departmentName,
                                                     String mainsectionName, String subsectionName, BigInteger productCode,
                                                     BigInteger saleNumber, String ean13, String dun14) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null
                && productCode == null && saleNumber == null && ean13 == null) && dun14 != null) {
            query = "WHERE hgi.dun14 = :dun14 ";
        } else {
            if (dun14 != null) {
                query = "AND hgi.dun14 = :dun14 ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForSaleNumber(String providerProductName, String departmentName,
                                                          String mainsectionName, String subsectionName, BigInteger productCode,
                                                          BigInteger saleNumber) {
        var query = "";
        if ((providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null
                && productCode == null) && saleNumber != null) {
            query = "WHERE hgi.saleNumber = :saleNumber ";
        } else {
            if (saleNumber != null) {
                query = "AND hgi.saleNumber = :saleNumber ";
            }
        }
        return query;
    }

    private String configureWhereOrAndClauseForFromAndTo(String providerProductName, String departmentName,
                                                         String mainsectionName, String subsectionName, BigInteger productCode,
                                                         String ean13, String dun14, BigInteger saleNumber) {
        var query = "";
        if (providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null
                && productCode == null && saleNumber == null && ean13 == null && dun14 == null) {
            query = "WHERE hgi.registrationDate BETWEEN :from AND :to ";
        } else {
            query = "AND hgi.registrationDate BETWEEN :from AND :to ";
        }
        return query;
    }

    private String configureWhereOrAndClauseForIsEffectiveSale(String providerProductName, String departmentName,
                                                               String mainsectionName, String subsectionName, BigInteger productCode,
                                                               String ean13, String dun14, BigInteger saleNumber,
                                                               Timestamp from, Timestamp to) {
        var query = "";
        if (providerProductName == null && departmentName == null && mainsectionName == null && subsectionName == null
                && productCode == null && ean13 == null && dun14 == null && saleNumber == null && from.toInstant() == null
                && to.toInstant() == null) {
            query = "WHERE hgi.isEffectiveSale = :isEffectiveSale";
        } else {
            query = "AND hgi.isEffectiveSale = :isEffectiveSale";
        }
        return query;
    }

    private TypedQuery<BigDecimal> setParameters(TypedQuery<BigDecimal> createQuery, String providerProductName,
                                                 String departmentName, String mainsectionName, String subsectionName,
                                                 BigInteger productCode, String ean13, String dun14, BigInteger saleNumber,
                                                 Timestamp from, Timestamp to, boolean isEffectiveSale) {
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
        if (ean13 != null) {
            createQuery.setParameter("ean13", ean13);
        }
        if (dun14 != null) {
            createQuery.setParameter("dun14", dun14);
        }
        if (saleNumber != null) {
            createQuery.setParameter("saleNumber", saleNumber);
        }
        if (from.toInstant() != null && to.toInstant() != null) {
            createQuery.setParameter("from", from);
            createQuery.setParameter("to", to);
        }
        createQuery.setParameter("isEffectiveSale", isEffectiveSale);

        return createQuery;
    }
}
