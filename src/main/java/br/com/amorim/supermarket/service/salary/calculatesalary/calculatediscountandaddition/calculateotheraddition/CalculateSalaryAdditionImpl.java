package br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotheraddition;

import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class CalculateSalaryAdditionImpl implements CalculateSalaryAddition {

    @Override
    public BigDecimal calculateAddition(Salary salary) {
        final BigDecimal[] additions = {BigDecimal.ZERO};
        if (!salary.getOtherAdditions().isEmpty()) {
            salary.getOtherAdditions().forEach(addition ->
                    additions[0] = additions[0].add(addition.getAdditionValue())
            );
        }
        return additions[0];
    }
}
