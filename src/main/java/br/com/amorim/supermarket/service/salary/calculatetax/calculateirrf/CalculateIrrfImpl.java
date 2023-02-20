package br.com.amorim.supermarket.service.salary.calculatetax.calculateirrf;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatetax.calculateinss.CalculateInss;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_DEDUCTION_AMOUNT_RANGE_2;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_DEDUCTION_AMOUNT_RANGE_3;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_DEDUCTION_AMOUNT_RANGE_4;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_DEDUCTION_AMOUNT_RANGE_5;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_NO_TAX_RANGE;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_RANGE_1;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_RANGE_2;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_RANGE_3;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_RANGE_4;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_RANGE_5;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_TAX_RANGE_2;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_TAX_RANGE_3;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_TAX_RANGE_4;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.IRRF_TAX_RANGE_5;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.NEW_SCALE_TWO;
import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.ZERO;

@AllArgsConstructor

@Component
public class CalculateIrrfImpl implements CalculateIrrf {

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
        var getInss = calculateInss.calculateInssValue(salary);
        var grossSalary = salary.getGrossSalary().subtract(getInss);

        var rangeOne = grossSalary.compareTo(IRRF_RANGE_1) < ZERO || salary.getGrossSalary().compareTo(IRRF_RANGE_1) == ZERO;
        var rangeTwo = grossSalary.compareTo(IRRF_RANGE_2) < ZERO || salary.getGrossSalary().compareTo(IRRF_RANGE_2) == ZERO;
        var rangeThree = grossSalary.compareTo(IRRF_RANGE_3) < ZERO || salary.getGrossSalary().compareTo(IRRF_RANGE_3) == ZERO;
        var rangeFour = grossSalary.compareTo(IRRF_RANGE_4) < ZERO || salary.getGrossSalary().compareTo(IRRF_RANGE_4) == ZERO;
        var rangeFive = grossSalary.compareTo(IRRF_RANGE_5) > ZERO || salary.getGrossSalary().compareTo(IRRF_RANGE_5) == ZERO;

        if (rangeOne) {
            return IRRF_NO_TAX_RANGE;
        } else if (rangeTwo) {
            BigDecimal irrfRangeTwo = grossSalary.multiply(IRRF_TAX_RANGE_2);
            return irrfRangeTwo.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_2)
                    .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE : irrfRangeTwo.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_2);
        } else if (rangeThree) {
            BigDecimal irrfRangeThree = grossSalary.multiply(IRRF_TAX_RANGE_3);
            return irrfRangeThree.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_3)
                    .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE : irrfRangeThree.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_3);
        } else if (rangeFour){
            BigDecimal irrfRangeFour = grossSalary.multiply(IRRF_TAX_RANGE_4);
            return irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_4)
                    .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE : irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_4);
        } else if (rangeFive) {
            BigDecimal irrfRangeFour = grossSalary.multiply(IRRF_TAX_RANGE_5);
            return irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_5)
                    .compareTo(IRRF_NO_TAX_RANGE) < ZERO ? IRRF_NO_TAX_RANGE : irrfRangeFour.subtract(IRRF_DEDUCTION_AMOUNT_RANGE_5);
        } else {
            return IRRF_NO_TAX_RANGE;
        }
    }
}
