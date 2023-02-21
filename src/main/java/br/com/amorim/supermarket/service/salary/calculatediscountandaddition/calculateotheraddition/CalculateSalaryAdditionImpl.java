package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.calculateotheraddition;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.otheraddition.OtherAdditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class CalculateSalaryAdditionImpl implements CalculateSalaryAddition {

    private OtherAdditionRepository otherAdditionRepository;

    @Override
    public BigDecimal calculateAddition(Salary salary) {
        final BigDecimal[] additions = {BigDecimal.ZERO};
        if (!salary.getOtherAdditions().isEmpty()) {
            salary.getOtherAdditions().forEach(addition ->
                    otherAdditionRepository.findById(addition.getId())
                            .map(existentAddition-> {
                                additions[0] = additions[0].add(existentAddition.getAdditionValue());
                                return existentAddition;
                            }));
        }
        return additions[0];
    }
}
