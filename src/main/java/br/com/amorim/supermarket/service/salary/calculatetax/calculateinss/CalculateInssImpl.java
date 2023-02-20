package br.com.amorim.supermarket.service.salary.calculatetax.calculateinss;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatetax.calculateinss.progressiverate.CalculateProgressiveRate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_NO_TAX_RANGE;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_RANGE_1;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_RANGE_2;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_RANGE_3;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_RANGE_4;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_RANGE_5;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_TAX_RANGE_1;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_TAX_RANGE_2;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_TAX_RANGE_3;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.INSS_TAX_RANGE_4;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.ZERO;

@AllArgsConstructor

@Component
public class CalculateInssImpl implements CalculateInss {

    private CalculateProgressiveRate calculateProgressiveRate;

    /**
     *  Base 2023 para trabalhadores CLT
     *         Salário (de)	Salário (até)	Alíquota
     *         0,00	        1.302,00	    7,5%
     *         1.302,01	    2.571,29	    9,0%
     *         2.571,30	    3.856,94	    12,0%
     *         3.856,95	    7.507,49        14,0%
     * @see br.com.amorim.supermarket.service.salary.calculatetax.calculateinss.progressiverate.CalculateProgressiveRateImpl
     * @param salary entidade salário que irá prover o salário bruto para o calculo
     * @return retorna o valor total do INSS a pagar
     */
    @Override
    public BigDecimal calculateInssValue(Salary salary) {
        var rangeOne = salary.getGrossSalary().compareTo(INSS_RANGE_1) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_1) == ZERO;
        var rangeTwo = salary.getGrossSalary().compareTo(INSS_RANGE_2) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_2) == ZERO;
        var rangeThree = salary.getGrossSalary().compareTo(INSS_RANGE_3) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_3) == ZERO;
        var rangeFour = salary.getGrossSalary().compareTo(INSS_RANGE_4) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_4) == ZERO;
        var rangeFive = salary.getGrossSalary().compareTo(INSS_RANGE_5) > ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_5) == ZERO;

        if (rangeOne) {
            return calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_1);
        } else if (rangeTwo) {
            return calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_2);
        } else if (rangeThree) {
            return calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_3);
        } else if (rangeFour){
            return calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_4);
        } else if (rangeFive){
            return calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_4);
        } else {
            return INSS_NO_TAX_RANGE;
        }
    }
}
