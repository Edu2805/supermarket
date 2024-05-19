package br.com.amorim.supermarket.controller.financialstatementreport.dto.sales.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialSalesReportOutput {

    private BigDecimal result;
}
