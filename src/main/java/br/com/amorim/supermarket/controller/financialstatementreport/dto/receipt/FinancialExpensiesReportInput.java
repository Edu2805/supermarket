package br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class FinancialExpensiesReportInput {

    private String providerProductName;
    private String departmentName;
    private String mainsectionName;
    private String subsectionName;
    private BigInteger productCode;
    private String invoice;
    private LocalDate from;
    private LocalDate to;
    @JsonProperty
    private boolean isReceived;
}
