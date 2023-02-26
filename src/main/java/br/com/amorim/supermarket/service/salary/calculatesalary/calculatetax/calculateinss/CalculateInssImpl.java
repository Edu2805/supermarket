package br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.progressiverate.CalculateProgressiveRateImpl;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.progressiverate.CalculateProgressiveRate;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.utils.TaxRangeConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class CalculateInssImpl extends TaxRangeConstants implements CalculateInss {

    private CalculateProgressiveRate calculateProgressiveRate;

    /**
     *  Base 2023 para trabalhadores CLT
     *         Salário (de)	Salário (até)	Alíquota
     *         0,00	        1.302,00	    7,5%
     *         1.302,01	    2.571,29	    9,0%
     *         2.571,30	    3.856,94	    12,0%
     *         3.856,95	    7.507,49        14,0%
     * @see CalculateProgressiveRateImpl
     * @param salary entidade salário que irá prover o salário bruto para o calculo
     * @return retorna o valor total do INSS a pagar
     */
    @Override
    public BigDecimal calculateInssValue(Salary salary) {
        BigDecimal calculateInssValue;
        var rangeOne = salary.getGrossSalary().compareTo(INSS_RANGE_1) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_1) == ZERO;
        var rangeTwo = salary.getGrossSalary().compareTo(INSS_RANGE_2) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_2) == ZERO;
        var rangeThree = salary.getGrossSalary().compareTo(INSS_RANGE_3) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_3) == ZERO;
        var rangeFour = salary.getGrossSalary().compareTo(INSS_RANGE_4) < ZERO || salary.getGrossSalary().compareTo(INSS_RANGE_4) == ZERO;

        if (rangeOne) {
            calculateInssValue = calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_1);
        } else if (rangeTwo) {
            calculateInssValue = calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_2);
        } else if (rangeThree) {
            calculateInssValue = calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_3);
        } else if (rangeFour){
            calculateInssValue = calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_4);
        } else {
            calculateInssValue = calculateProgressiveRate.progressiveCalculation(salary, INSS_TAX_RANGE_4);
        }
        return calculateInssValue;
    }
}
