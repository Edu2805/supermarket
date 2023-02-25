package br.com.amorim.supermarket.service.salary.verifyduplicatesalary;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
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

class VerifyDuplicateSalaryImplTest {

    @InjectMocks
    private VerifyDuplicateSalaryImpl verifyDuplicateSalary;
    @Mock
    private SalaryRepository salaryRepository;
    private Salary salary;

    private void startSalary() {
        var salaryTest = new SalaryTest();
        salary = salaryTest.generateSalary();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSalary();
    }

    @Test
    void shouldReturnFalseWhenNotExistsDuplicateSalary() {
        when(salaryRepository.findByPositionAndSalaryRange(salary.getPosition(), salary.getSalaryRange()))
                .thenReturn(Optional.empty());
        var isDuplicate = verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary);
        assertFalse(isDuplicate);
    }

    @Test
    void shouldReturnMessageExceptionWhenExistsDuplicateSalary() {
        var expectedMessage = getString(MessagesKeyType.SALARY_IS_DUPLICATED.message);
        when(salaryRepository.findByPositionAndSalaryRange(salary.getPosition(), salary.getSalaryRange()))
                .thenReturn(Optional.of(salary));
        var messageException = assertThrows(BusinessRuleException.class, () ->
                        verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary)
                ).getMessage();
        assertEquals(expectedMessage, messageException);
    }
}