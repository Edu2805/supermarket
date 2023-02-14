package br.com.amorim.supermarket.testutils.generateentitiesunittests.jobposition;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;

import java.math.BigInteger;
import java.util.UUID;

public class JobPositionTest {

    public JobPosition generateJobPosition() {
        SalaryTest salaryTest = new SalaryTest();
        JobPosition jobPosition = new JobPosition();

        jobPosition.setId(UUID.randomUUID());
        jobPosition.setName("Cargo Test");
        jobPosition.setCode(BigInteger.valueOf(1));
        jobPosition.setSalary(salaryTest.generateSalary());
        jobPosition.setAssignments("Fazer testes");
        return jobPosition;
    }
}
