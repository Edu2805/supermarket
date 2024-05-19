package br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoricalGoodsIssueReportOutput {
    private String name;
    private BigInteger productCode;
    private String ean13;
    private String dun14;
    private BigDecimal salePrice;
    private BigDecimal inventory;
    private String providerProductName;
    private String departmentName;
    private String mainsectionName;
    private String subsectionName;
    private BigInteger saleNumber;
    private BigDecimal productsTotal;
    private boolean isEffectiveSale;
    private Timestamp registrationDate;
}
