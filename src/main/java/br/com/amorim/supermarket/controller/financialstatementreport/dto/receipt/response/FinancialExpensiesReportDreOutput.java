package br.com.amorim.supermarket.controller.financialstatementreport.dto.receipt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialExpensiesReportDreOutput {

    private BigDecimal directExpensesResult;
    private BigDecimal indirectExpensesResult;
    private BigDecimal taxesResult;
    private BigDecimal financialExpensesResult;
}
