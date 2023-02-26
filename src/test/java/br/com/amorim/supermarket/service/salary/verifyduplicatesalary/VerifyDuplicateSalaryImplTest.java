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

import java.util.List;
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
    private Salary salary1;
    private Salary salary2;

    private void startSalary() {
        var salaryTest1 = new SalaryTest();
        var salaryTest2 = new SalaryTest();
        salary1 = salaryTest1.generateSalary();
        salary2 = salaryTest2.generateSalary();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSalary();
    }

    @Test
    void shouldReturnFalseWhenNotExistsDuplicateSalaryBeforeSave() {
        when(salaryRepository.findByPositionAndSalaryRange(salary1.getPosition(), salary1.getSalaryRange()))
                .thenReturn(Optional.empty());
        var isDuplicate = verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary1);
        assertFalse(isDuplicate);
    }

    @Test
    void shouldReturnMessageExceptionWhenExistsDuplicateSalaryBeforeSave() {
        var expectedMessage = getString(MessagesKeyType.SALARY_IS_DUPLICATED.message);
        when(salaryRepository.findByPositionAndSalaryRange(salary1.getPosition(), salary1.getSalaryRange()))
                .thenReturn(Optional.of(salary1));
        var messageException = assertThrows(BusinessRuleException.class, () ->
                        verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary1)
                ).getMessage();
        assertEquals(expectedMessage, messageException);
    }

    @Test
    void shouldReturnFalseWhenNotExistsDuplicateSalaryBeforeUpdate() {
        when(salaryRepository.findAll()).thenReturn(List.of(salary2));
        var isDuplicate = verifyDuplicateSalary.isDuplicateSalaryBeforeUpdate(salary2);
        assertFalse(isDuplicate);
    }

    @Test
    void shouldReturnMessageExceptionWhenExistsDuplicateSalaryBeforeUpdate() {
        salary2.setPosition(salary1.getPosition());
        salary2.setSalaryRange(salary1.getSalaryRange());
        var expectedMessage = getString(MessagesKeyType.SALARY_IS_DUPLICATED.message);

        when(salaryRepository.findAll()).thenReturn(List.of(salary2));
        var messageException = assertThrows(BusinessRuleException.class, () ->
                verifyDuplicateSalary.isDuplicateSalaryBeforeUpdate(salary1)
        ).getMessage();

        assertEquals(expectedMessage, messageException);
    }
}