package br.com.amorim.supermarket.testutils.generateentitiesunittests.salary;

import br.com.amorim.supermarket.model.salary.Salary;

import java.math.BigDecimal;
import java.util.UUID;

public class SalaryTest {

    public Salary generateSalary() {
        Salary salary = new Salary();

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
        return salary;
    }
}
