package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.calculateotherdiscount;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.otherdiscount.OtherDiscountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor

@Component
public class CalculateSalaryDiscountImpl implements CalculateSalaryDiscount {

    private OtherDiscountRepository otherDiscountRepository;

    @Override
    public BigDecimal calculateDiscount(Salary salary) {
        final BigDecimal[] discounts = {BigDecimal.ZERO};
        if (!salary.getOtherDiscounts().isEmpty()) {
            salary.getOtherDiscounts().forEach(discount ->
                    otherDiscountRepository.findById(discount.getId())
                            .map(existentDiscount-> {
                                discounts[0] = discounts[0].add(existentDiscount.getDiscountValue());
                                return existentDiscount;
                            }));
        }
        return discounts[0];
    }
}
