package br.com.amorim.supermarket.service.salary.calculatetax;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.calculatetax.calculatefgts.CalculateFgts;
import br.com.amorim.supermarket.service.salary.calculatetax.calculateinss.CalculateInss;
import br.com.amorim.supermarket.service.salary.calculatetax.calculateirrf.CalculateIrrf;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.NEW_SCALE_TWO;

@AllArgsConstructor

@Component
public class CalculateTaxImpl implements CalculateTax {

    private CalculateInss calculateInss;
    private CalculateFgts calculateFgts;
    private CalculateIrrf calculateIrrf;

    @Override
    public void calculateNetSalary(Salary salary) {
        salary.setNetSalary(salary.getGrossSalary());
        var inss = calculateInss.calculateInssValue(salary);
        var irrf = calculateIrrf.calculateIrrfValue(salary);
        var fgts = calculateFgts.calculateFgtsValue(salary);
        var netSalary = salary.getGrossSalary().subtract(inss).subtract(irrf);

        salary.setInss(inss.setScale(NEW_SCALE_TWO, RoundingMode.HALF_EVEN));
        salary.setFgts(fgts.setScale(NEW_SCALE_TWO, RoundingMode.HALF_EVEN));
        salary.setIrrf(irrf.setScale(NEW_SCALE_TWO, RoundingMode.HALF_EVEN));
        salary.setNetSalary(netSalary.setScale(NEW_SCALE_TWO, RoundingMode.HALF_EVEN));
    }
}
