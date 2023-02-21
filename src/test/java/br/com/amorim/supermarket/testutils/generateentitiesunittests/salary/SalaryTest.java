package br.com.amorim.supermarket.testutils.generateentitiesunittests.salary;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otheraddition.OtherAdditionTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otherdiscount.OtherDiscountTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SalaryTest {

    public Salary generateSalary() {
        Salary salary = new Salary();
        OtherAdditionTest otherAdditionTest = new OtherAdditionTest();
        OtherDiscountTest otherDiscountTest = new OtherDiscountTest();

        salary.setId(UUID.randomUUID());
        salary.setPosition("Cargo de testes");
        salary.setNetSalary(BigDecimal.valueOf(0.01));
        salary.setSalaryAdvance(BigDecimal.valueOf(0.01));
        salary.setGrossSalary(BigDecimal.valueOf(0.01));
        salary.setSalaryRange("Teste Range");
        salary.setInss(BigDecimal.valueOf(0.01));
        salary.setFgts(BigDecimal.valueOf(0.01));
        salary.setIrrf(BigDecimal.valueOf(0.01));
        salary.setBenefits("Beneficios do teste");
        salary.setOtherAdditions(List.of(otherAdditionTest.generateOtherAddition()));
        salary.setOtherDiscounts(List.of(otherDiscountTest.generateOtherDiscount()));
        return salary;
    }
}
