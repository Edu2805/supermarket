package br.com.amorim.supermarket.service.jobposition.generateinternalcode;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.jobposition.generateinternalcoderepositorycustom.GenerateInternalCodeJobPositionRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.jobposition.JobPositionTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GenerateInternalCodeJobPositionImplTest {

    @InjectMocks
    private GenerateInternalCodeJobPositionImpl generateInternalCodeJobPosition;
    @Mock
    private GenerateInternalCodeJobPositionRepositoryCustom generateInternalCodeJobPositionRepositoryCustom;

    private JobPosition jobPosition1;
    private JobPosition jobPosition2;
    private JobPosition jobPosition3;
    private static final BigInteger INTERNAL_CODE_ONE = BigInteger.valueOf(1);
    private static final BigInteger INTERNAL_CODE_TWO = BigInteger.valueOf(2);
    private static final BigInteger INTERNAL_CODE_THREE = BigInteger.valueOf(3);

    private void startJobPosition() {
        var jobPositionTest1 = new JobPositionTest();
        var jobPositionTest2 = new JobPositionTest();
        var jobPositionTest3 = new JobPositionTest();
        jobPosition1 = jobPositionTest1.generateJobPosition();
        jobPosition2 = jobPositionTest2.generateJobPosition();
        jobPosition3 = jobPositionTest3.generateJobPosition();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startJobPosition();
    }

    @Test
    void shouldGenerateInternalCode() {
        when(generateInternalCodeJobPositionRepositoryCustom.generateInternalCode(jobPosition1))
                .thenReturn(INTERNAL_CODE_ONE);
        when(generateInternalCodeJobPositionRepositoryCustom.generateInternalCode(jobPosition2))
                .thenReturn(INTERNAL_CODE_TWO);
        when(generateInternalCodeJobPositionRepositoryCustom.generateInternalCode(jobPosition3))
                .thenReturn(INTERNAL_CODE_THREE);
        var generateInternalCode = generateInternalCodeJobPosition.generate(jobPosition3);

        assertEquals(INTERNAL_CODE_THREE, generateInternalCode);
    }
}