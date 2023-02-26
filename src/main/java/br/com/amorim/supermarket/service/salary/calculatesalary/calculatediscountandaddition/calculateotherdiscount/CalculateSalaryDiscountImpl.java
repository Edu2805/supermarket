package br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotherdiscount;

import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class CalculateSalaryDiscountImpl implements CalculateSalaryDiscount {

    @Override
    public BigDecimal calculateDiscount(Salary salary) {
        final BigDecimal[] discounts = {BigDecimal.ZERO};
        if (!salary.getOtherDiscounts().isEmpty()) {
            salary.getOtherDiscounts().forEach(discount ->
                    discounts[0] = discounts[0].add(discount.getDiscountValue())
            );
        }
        return discounts[0];
    }
}
