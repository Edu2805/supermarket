package br.com.amorim.supermarket.service.salary.calculatediscountandaddition.calculateotheraddition;

import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.otheraddition.OtherAdditionRepository;
import br.com.amorim.supermarket.service.salary.calculatesalary.calculatediscountandaddition.calculateotheraddition.CalculateSalaryAdditionImpl;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otheraddition.OtherAdditionTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.salary.SalaryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CalculateSalaryAdditionImplTest {

    @InjectMocks
    private CalculateSalaryAdditionImpl calculateSalaryAddition;
    @Mock
    private OtherAdditionRepository otherAdditionRepository;
    private Salary salary;
    private OtherAddition otherAddition1;
    private OtherAddition otherAddition2;

    private void startSalary() {
        var salaryTest = new SalaryTest();
        var otherAdditionTest1 = new OtherAdditionTest();
        var otherAdditionTest2 = new OtherAdditionTest();
        otherAddition1 = otherAdditionTest1.generateOtherAddition();
        otherAddition2 = otherAdditionTest2.generateOtherAddition();
        salary = salaryTest.generateSalary();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSalary();
    }

    @Test
    void shouldSumTotalAdditionsWhenOtherAdditionExists() {
        var expectedValue = BigDecimal.valueOf(400.00);
        otherAddition1.setAdditionValue(BigDecimal.valueOf(100.00));
        otherAddition2.setAdditionValue(BigDecimal.valueOf(300.00));
        salary.setOtherAdditions(List.of(otherAddition1, otherAddition2));

        when(otherAdditionRepository.findById(otherAddition1.getId()))
                .thenReturn(Optional.of(otherAddition1));
        when(otherAdditionRepository.findById(otherAddition2.getId()))
                .thenReturn(Optional.of(otherAddition2));
        var calculateSum = calculateSalaryAddition.calculateAddition(salary);

        assertEquals(expectedValue, calculateSum);
    }

    @Test
    void shouldSumZeroWhenOtherAdditionNotExists() {
        var expectedValue = BigDecimal.ZERO;
        salary.setOtherAdditions(List.of());
        when(otherAdditionRepository.findById(otherAddition1.getId()))
                .thenReturn(Optional.empty());
        var calculateSum = calculateSalaryAddition.calculateAddition(salary);

        assertEquals(expectedValue, calculateSum);
    }
}