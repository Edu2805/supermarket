package br.com.amorim.supermarket.service.jobposition.filljobpositionname.fillname;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class FillPositionNameBySalaryImplTest {

    @InjectMocks
    private FillPositionNameBySalaryImpl fillPositionNameBySalary;
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
    void shouldFillPositionNameWithSuccess() {
        salary.setPosition("Salário teste");
        when(salaryRepository.findById(jobPosition.getSalary().getId()))
                .thenReturn(Optional.of(salary));
        fillPositionNameBySalary.fillPositionName(jobPosition);

        assertEquals(salary.getPosition(), jobPosition.getName());
    }

    @Test
    void shouldNotFillPositionNameWhenSalaryNotExistsInJobPosition() {
        salary.setPosition("Salário teste");
        when(salaryRepository.findById(jobPosition.getSalary().getId()))
                .thenReturn(Optional.empty());
        fillPositionNameBySalary.fillPositionName(jobPosition);

        assertNotEquals(salary.getPosition(), jobPosition.getName());
    }
}