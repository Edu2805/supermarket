package br.com.amorim.supermarket.service.jobposition.filljobpositionname.verifyjobpositionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.jobposition.JobPositionTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VerifyJobPositionNameImplTest {

    @InjectMocks
    private VerifyJobPositionNameImpl verifyJobPositionName;
    @Mock
    private SalaryRepository salaryRepository;
    private JobPosition jobPosition;
    private Salary salary;

    private void startJobPosition() {
        var jobPositionTest = new JobPositionTest();
        var salaryTest = new SalaryTest();
        salary = salaryTest.generateSalary();
        jobPosition = jobPositionTest.generateJobPosition();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startJobPosition();
    }

    @Test
    void shouldReturnFalseWhenPositionNameNotExists() {
        when(salaryRepository.findByPosition(jobPosition.getName()))
                .thenReturn(Optional.empty());
        boolean existsInSalary = verifyJobPositionName
                .isPositionNameAlreadyExistsInSalary(jobPosition);
        assertFalse(existsInSalary);
    }

    @Test
    void shouldReturnExceptionWhenPositionNameAlreadyExists() {
        var messageExpected = getString(MessagesKeyType.JOB_POSITION_NAME_ALREADY_EXISTS.message);
        when(salaryRepository.findByPosition(jobPosition.getName()))
                .thenReturn(Optional.of(salary));
        var messageException = assertThrows(BusinessRuleException.class, () ->
                verifyJobPositionName.isPositionNameAlreadyExistsInSalary(jobPosition)
        ).getMessage();
        assertEquals(messageExpected, messageException);
    }
}