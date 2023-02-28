package br.com.amorim.supermarket.repository.jobposition.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import br.com.amorim.supermarket.repository.jobposition.JobPositionRepository;
import br.com.amorim.supermarket.repository.otheraddition.OtherAdditionRepository;
import br.com.amorim.supermarket.repository.otherdiscount.OtherDiscountRepository;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("classpath:application.properties")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class GenerateInternalCodeJobPositionRepositoryCustomImplTest {

    @Autowired
    private GenerateInternalCodeJobPositionRepositoryCustomImpl generateInternalCodeJobPositionRepositoryCustom;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;
    @Autowired
    private JobPositionRepository jobPositionRepository;
    @Autowired
    private OtherAdditionRepository otherAdditionRepository;
    @Autowired
    private OtherDiscountRepository otherDiscountRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private OtherAddition otherAddition1;
    private OtherAddition otherAddition2;
    private OtherAddition otherAddition3;
    private OtherDiscount otherDiscount1;
    private OtherDiscount otherDiscount2;
    private OtherDiscount otherDiscount3;
    private Salary salary1;
    private Salary salary2;
    private Salary salary3;
    private JobPosition jobPosition1;
    private JobPosition jobPosition2;
    private JobPosition jobPosition3;

    private void startJobPosition() {
        otherAddition1 = generateEntitiesRepository.generateOtherAddition();
        otherAddition2 = generateEntitiesRepository.generateOtherAddition();
        otherAddition3 = generateEntitiesRepository.generateOtherAddition();
        otherDiscount1 = generateEntitiesRepository.generateOtherDiscount();
        otherDiscount2 = generateEntitiesRepository.generateOtherDiscount();
        otherDiscount3 = generateEntitiesRepository.generateOtherDiscount();
        salary1 = generateEntitiesRepository.generateSalary(otherAddition1, otherDiscount1);
        salary2 = generateEntitiesRepository.generateSalary(otherAddition2, otherDiscount2);
        salary3 = generateEntitiesRepository.generateSalary(otherAddition3, otherDiscount3);
        jobPosition1 = generateEntitiesRepository.generateJobPosition(salary1);
        jobPosition2 = generateEntitiesRepository.generateJobPosition(salary2);
        jobPosition3 = generateEntitiesRepository.generateJobPosition(salary3);
    }

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        jobPositionRepository.deleteAll();
        startJobPosition();
    }

    private void deleteJobPosition() {
        otherAdditionRepository.delete(otherAddition1);
        otherAdditionRepository.delete(otherAddition2);
        otherAdditionRepository.delete(otherAddition3);
        otherDiscountRepository.delete(otherDiscount1);
        otherDiscountRepository.delete(otherDiscount2);
        otherDiscountRepository.delete(otherDiscount3);
        salaryRepository.delete(salary1);
        salaryRepository.delete(salary2);
        salaryRepository.delete(salary3);
        jobPositionRepository.delete(jobPosition1);
        jobPositionRepository.delete(jobPosition2);
        jobPositionRepository.delete(jobPosition3);
    }

    @AfterEach
    void cleanUp() {
        deleteJobPosition();
    }

    @Transactional
    @Test
    void shouldSetTheJobPositionWithTheInternalCodeEqualToOne() {
        assertEquals(BigInteger.valueOf(1), jobPosition1.getCode());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        assertEquals(BigInteger.valueOf(3), jobPosition3.getCode());
    }
}