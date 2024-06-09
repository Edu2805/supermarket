package br.com.amorim.supermarket.repository.jobposition.jobpositionrepositorycustom;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class GenerateInternalCodeJobPositionRepositoryCustomImplTest {

    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;

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
        startJobPosition();
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