package br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateirrf;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.utils.TaxRangeConstants;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatetax.calculateinss.CalculateInss;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class CalculateIrrfImpl extends TaxRangeConstants implements CalculateIrrf {

    private CalculateInss calculateInss;

    /*
    Referência jan/2013
    Base de cálculo	                        Alíquota	Parcela a deduzir
                        Até R$1.903,98	    Isento	    Isento
    De R$1.903,99       até R$2.826,65	    7,5%	    R$ 142,80
    De R$2.826,66       até R$3.751,05	    15%	        R$ 354,80
    De R$3.751,06       até R$4.664,68	    22,5%	    R$ 636,13
            Acima de R$4.664,68	            27,5%	    R$ 869,36
     */
    @Override
    public BigDecimal calculateIrrfValue(Salary salary) {
        // todo aplicar dedução quando a pessoa possuir dependentes
        BigDecimal irrfValue;
        var getInss = calculateInss.calculateInssValue(salary);
        var grossSalary = salary.getGrossSalary().subtract(getInss);

        var rangeOne = isRangeOne(grossSalary, salary);
        var rangeTwo = isRangeTwo(grossSalary, salary);
        var rangeThree = isRangeThree(grossSalary, salary);
        var rangeFour = isRangeFour(grossSalary, salary);

        if (rangeOne) {
            irrfValue = IRRF_NO_TAX_RANGE;
        } else if (rangeTwo) {
            irrfValue = calculateRangeTwo(grossSalary);
        } else if (rangeThree) {
            irrfValue = calculateRangeThree(grossSalary);
        } else if (rangeFour){
            irrfValue = calculateRangeFour(grossSalary);
        } else {
            irrfValue = calculateRangeFive(grossSalary);
        }
        return irrfValue;
    }

    private boolean isRangeOne(BigDecimal grossSalary, Salary salary) {
        return grossSalary.compareTo(IRRF_RANGE_1) < ZERO
                || salary.getGrossSalary().compareTo(IRRF_RANGE_1) == ZERO;
    }

    private boolean isRangeTwo(BigDecimal grossSalary, Salary salary) {
        return grossSalary.compareTo(IRRF_RANGE_2) < ZERO
                || salary.getGrossSalary().compareTo(IRRF_RANGE_2) == ZERO;
    }

    private boolean isRangeThree(BigDecimal grossSalary, Salary salary) {
        return grossSalary.compareTo(IRRF_RANGE_3) < ZERO
                || salary.getGrossSalary().compareTo(IRRF_RANGE_3) == ZERO;
    }

    private boolean isRangeFour(BigDecimal grossSalary, Salary salary) {
        return grossSalary.compareTo(IRRF_RANGE_4) < ZERO
                || salary.getGrossSalary().compareTo(IRRF_RANGE_4) == ZERO;
    }

    private BigDecimal calculateRangeTwo(BigDecimal grossSalary) {
        var irrfRangeTwo = grossSalary.multiply(IRRF_TAX_RANGE_2);
        return irrfRangeTwo.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_2)
                .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE
                : irrfRangeTwo.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_2);
    }

    private BigDecimal calculateRangeThree(BigDecimal grossSalary) {
        var irrfRangeThree = grossSalary.multiply(IRRF_TAX_RANGE_3);
        return irrfRangeThree.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_3)
                .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE
                : irrfRangeThree.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_3);
    }

    private BigDecimal calculateRangeFour(BigDecimal grossSalary) {
        var irrfRangeFour = grossSalary.multiply(IRRF_TAX_RANGE_4);
        return irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_4)
                .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE
                : irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_4);
    }

    private BigDecimal calculateRangeFive(BigDecimal grossSalary) {
        var irrfRangeFour = grossSalary.multiply(IRRF_TAX_RANGE_5);
        return irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_5)
                .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE
                : irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_5);
    }
}
