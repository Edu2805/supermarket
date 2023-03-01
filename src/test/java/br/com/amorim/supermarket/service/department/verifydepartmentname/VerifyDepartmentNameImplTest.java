package br.com.amorim.supermarket.service.department.verifydepartmentname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.department.DepartmentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VerifyDepartmentNameImplTest {

    @InjectMocks
    private VerifyDepartmentNameImpl verifyDepartmentName;
    @Mock
    private DepartmentRepository departmentRepository;

    private Department department1;
    private Department department2;
    private final String EXPECTED_MESSAGE = getString(MessagesKeyType.DEPARTMENT_NAME_ALREADY_EXISTS.message);

    private void startDepartment() {
        var departmentTest1 = new DepartmentTest();
        var departmentTest2 = new DepartmentTest();
        department1 = departmentTest1.generateDepartment();
        department2 = departmentTest2.generateDepartment();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startDepartment();
    }

    @Test
    void shouldReturnFalseWhenDepartmentNameNotExistsBeforeSave() {
        when(departmentRepository.existsByName(department1.getName()))
                .thenReturn(false);

        var existsByName = verifyDepartmentName.existsByDepartmentNameBeforeSave(department1);

        assertFalse(existsByName);
    }

    @Test
    void shouldReturnAExceptionMessageWhenDepartmentNameAlreadyExistsBeforeSave() {
        when(departmentRepository.existsByName(department1.getName()))
                .thenReturn(true);

        var messageExcpetion = assertThrows(BusinessRuleException.class, () ->
                        verifyDepartmentName.existsByDepartmentNameBeforeSave(department1)
        ).getMessage();

        assertEquals(EXPECTED_MESSAGE, messageExcpetion);
    }

    @Test
    void shouldReturnFalseWhenDepartmentNameNotExistsBeforeUpdate() {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department1);
        when(departmentRepository.findAll()).thenReturn(departmentList);

        var existsByName = verifyDepartmentName.existsByDepartmentNameBeforeUpdate(department1);

        assertFalse(existsByName);
    }

    @Test
    void shouldReturnAExceptionMessageWhenDepartmentNameAlreadyExistsBeforeUpdate() {
        List<Department> departmentList = new ArrayList<>();
        department1.setName(department2.getName());
        departmentList.add(department2);
        when(departmentRepository.findAll()).thenReturn(departmentList);

        var messageExcpetion = assertThrows(BusinessRuleException.class, () ->
                verifyDepartmentName.existsByDepartmentNameBeforeUpdate(department1)
        ).getMessage();

        assertEquals(EXPECTED_MESSAGE, messageExcpetion);
    }
}