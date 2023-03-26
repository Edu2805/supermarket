package br.com.amorim.supermarket.controller.financialstatementreport.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialSalesReportInput {

    private String providerProductName;
    private String departmentName;
    private String mainsectionName;
    private String subsectionName;
    private BigInteger productCode;
    private String ean13;
    private String dun14;
    private BigInteger saleNumber;
    private LocalDate from;
    private LocalDate to;
    private boolean isEffectiveSale;
}
