package br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

@AllArgsConstructor(access = AccessLevel.PROTECTED)

public abstract class TaxRangeConstants {

    protected static final int NEW_SCALE_TWO = 2;
    protected static final BigDecimal INSS_RANGE_1 = valueOf(1302.0);
    protected static final BigDecimal INSS_RANGE_2 = valueOf(2571.29);
    protected static final BigDecimal INSS_RANGE_3 = valueOf(3856.94);
    protected static final BigDecimal INSS_RANGE_4 = valueOf(7507.49);
    protected static final BigDecimal INSS_RANGE_5 = valueOf(7507.50);
    protected static final BigDecimal INSS_TAX_RANGE_1 = valueOf(0.075);
    protected static final BigDecimal INSS_TAX_RANGE_2 = valueOf(0.09);
    protected static final BigDecimal INSS_TAX_RANGE_3 = valueOf(0.12);
    protected static final BigDecimal INSS_TAX_RANGE_4 = valueOf(0.14);
    protected static final BigDecimal INSS_NO_TAX_RANGE = valueOf(0);

    protected static final BigDecimal IRRF_RANGE_1 = BigDecimal.valueOf(1903.98);
    protected static final BigDecimal IRRF_RANGE_2 = BigDecimal.valueOf(2826.65);
    protected static final BigDecimal IRRF_RANGE_3 = BigDecimal.valueOf(3751.05);
    protected static final BigDecimal IRRF_RANGE_4 = BigDecimal.valueOf(4664.68);
    protected static final BigDecimal IRRF_RANGE_5 = BigDecimal.valueOf(4664.69);
    protected static final BigDecimal IRRF_TAX_RANGE_2 = BigDecimal.valueOf(0.075);
    protected static final BigDecimal IRRF_TAX_RANGE_3 = BigDecimal.valueOf(0.15);
    protected static final BigDecimal IRRF_TAX_RANGE_4 = BigDecimal.valueOf(0.225);
    protected static final BigDecimal IRRF_TAX_RANGE_5 = BigDecimal.valueOf(0.275);
    protected static final BigDecimal IRRF_NO_TAX_RANGE = BigDecimal.valueOf(0);
    protected static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_2 = BigDecimal.valueOf(142.80);
    protected static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_3 = BigDecimal.valueOf(354.80);
    protected static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_4 = BigDecimal.valueOf(636.13);
    protected static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_5 = BigDecimal.valueOf(869.36);
    protected static final int ZERO = 0;
}
