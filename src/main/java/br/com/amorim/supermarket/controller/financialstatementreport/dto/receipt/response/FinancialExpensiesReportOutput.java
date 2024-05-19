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
public class FinancialExpensiesReportOutput {

    private BigDecimal result;
}
