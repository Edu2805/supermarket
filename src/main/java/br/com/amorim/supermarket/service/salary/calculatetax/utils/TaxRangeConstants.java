package br.com.amorim.supermarket.service.salary.calculatetax.utils;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class TaxRangeConstants {

    public static final int NEW_SCALE_TWO = 2;
    public static final BigDecimal INSS_RANGE_1 = valueOf(1302.0);
    public static final BigDecimal INSS_RANGE_2 = valueOf(2571.29);
    public static final BigDecimal INSS_RANGE_3 = valueOf(3856.94);
    public static final BigDecimal INSS_RANGE_4 = valueOf(7507.49);
    public static final BigDecimal INSS_RANGE_5 = valueOf(7507.50);
    public static final BigDecimal INSS_TAX_RANGE_1 = valueOf(0.075);
    public static final BigDecimal INSS_TAX_RANGE_2 = valueOf(0.09);
    public static final BigDecimal INSS_TAX_RANGE_3 = valueOf(0.12);
    public static final BigDecimal INSS_TAX_RANGE_4 = valueOf(0.14);
    public static final BigDecimal INSS_NO_TAX_RANGE = valueOf(0);

    public static final BigDecimal IRRF_RANGE_1 = BigDecimal.valueOf(1903.98);
    public static final BigDecimal IRRF_RANGE_2 = BigDecimal.valueOf(2826.65);
    public static final BigDecimal IRRF_RANGE_3 = BigDecimal.valueOf(3751.05);
    public static final BigDecimal IRRF_RANGE_4 = BigDecimal.valueOf(4664.68);
    public static final BigDecimal IRRF_RANGE_5 = BigDecimal.valueOf(4664.69);
    public static final BigDecimal IRRF_TAX_RANGE_2 = BigDecimal.valueOf(0.075);
    public static final BigDecimal IRRF_TAX_RANGE_3 = BigDecimal.valueOf(0.15);
    public static final BigDecimal IRRF_TAX_RANGE_4 = BigDecimal.valueOf(0.225);
    public static final BigDecimal IRRF_TAX_RANGE_5 = BigDecimal.valueOf(0.275);
    public static final BigDecimal IRRF_NO_TAX_RANGE = BigDecimal.valueOf(0);
    public static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_2 = BigDecimal.valueOf(142.80);
    public static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_3 = BigDecimal.valueOf(354.80);
    public static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_4 = BigDecimal.valueOf(636.13);
    public static final BigDecimal IRRF_DEDUCTION_AMOUNT_RANGE_5 = BigDecimal.valueOf(869.36);
    public static final int ZERO = 0;
}
