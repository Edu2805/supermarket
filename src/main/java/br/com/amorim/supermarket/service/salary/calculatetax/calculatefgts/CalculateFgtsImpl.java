package br.com.amorim.supermarket.service.salary.calculatetax.calculatefgts;

import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class CalculateFgtsImpl implements CalculateFgts {

    private static final BigDecimal FGTS = BigDecimal.valueOf(0.08);

    @Override
    public BigDecimal calculateFgtsValue(Salary salary) {
        return salary.getGrossSalary().multiply(FGTS);
    }
}
