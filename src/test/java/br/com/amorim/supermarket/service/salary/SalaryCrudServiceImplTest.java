package br.com.amorim.supermarket.service.salary;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import br.com.amorim.supermarket.service.salary.calculatesalary.CalculateSalary;
import br.com.amorim.supermarket.service.salary.verifyduplicatesalary.VerifyDuplicateSalary;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class SalaryCrudServiceImplTest {

    @InjectMocks
    private SalaryCrudServiceImpl salaryCrudService;
    @Mock
    private SalaryRepository salaryRepository;
    @Mock
    private VerifyDuplicateSalary verifyDuplicateSalary;
    @Mock
    private CalculateSalary calculateSalary;

    private Salary salary;
    private final ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

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
    void shouldFindById() {
        var knownId = salary.getId();
        when(salaryRepository.findById(knownId)).thenReturn(Optional.of(salary));

        var salaryFound = salaryCrudService.findById(knownId);

        assertEquals(knownId, salaryFound.getId());
    }

    @Test
    void shouldShowANotFoundExceptionMessageWhenFindByIdWithIncorrectId() {
        String messageError = getString(MessagesKeyType.SALARY_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

        when(salaryRepository.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(salary));

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {salaryCrudService.findById(salary.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () -> salaryCrudService.findById(salary.getId()));
    }

    @Test
    void shouldSaveSalaryWithSuccess() {
        when(verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary))
                .thenReturn(false);
        doNothing().when(calculateSalary).calculate(salary);
        when(salaryRepository.save(salary)).thenReturn(salary);

        var saveSalary = salaryCrudService.save(salary);

        assertNotNull(saveSalary);
        assertEquals(Salary.class, saveSalary.getClass());
        assertEquals(salary.getId(), saveSalary.getId());
    }

    @Test
    void shouldUpdateUserDataWithSuccess() {
        var knownId = salary.getId();

        when(salaryRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(salary));
        when(verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary))
                .thenReturn(false);
        doNothing().when(calculateSalary).calculate(salary);
        when(salaryRepository.save(salary)).thenReturn(salary);

        salaryCrudService.update(salary, knownId);

        assertEquals(knownId, knownIdCapture.getValue());
        assertEquals(salary.getPosition(), salaryRepository.save(salary).getPosition());
        assertEquals(salary.getClass(), salaryRepository.save(salary).getClass());
    }

    @Test
    void shouldNotUpdateWhenSalaryIsDuplicatedIsIncorrect() {
        String messageError = getString(MessagesKeyType.SALARY_IS_DUPLICATED.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

        when(salaryRepository.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(salary));
        when(verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary))
                .thenThrow(new BusinessRuleException(getString(MessagesKeyType
                        .SALARY_IS_DUPLICATED.message)));

        String exceptionMessage = Assertions.assertThrows(BusinessRuleException.class, () -> {
                    salaryCrudService.update(
                            salary, salary.getId());
                }
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                salaryCrudService.update(salary, salary.getId()));
    }

    @Test
    void shouldNotUpdateWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.SALARY_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(salaryRepository.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.empty());

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    salaryCrudService.update(
                            salary, salary.getId());
                }
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                salaryCrudService.update(salary, salary.getId()));
    }

    @Test
    void shouldDeleteWithSuccess() {
        var knownId = salary.getId();
        when(salaryRepository.findById(knownId))
                .thenReturn(Optional.of(salary));
        doNothing().when(salaryRepository).delete(salary);

        salaryCrudService.delete(knownId);

        verify(salaryRepository,
                times(1)).delete(salary);

    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.SALARY_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(salaryRepository.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(salary));
        doNothing().when(salaryRepository).delete(salary);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    salaryCrudService.delete(salary.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                salaryCrudService.delete(salary.getId()));
        verify(salaryRepository, times(0)).delete(salary);
    }
}