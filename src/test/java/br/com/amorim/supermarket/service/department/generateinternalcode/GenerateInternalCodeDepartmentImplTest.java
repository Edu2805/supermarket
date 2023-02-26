package br.com.amorim.supermarket.service.department.generateinternalcode;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.department.generateinternalcoderepositorycustom.GenerateInternalCodeDepartmentRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.department.DepartmentTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GenerateInternalCodeDepartmentImplTest {

    @InjectMocks
    private GenerateInternalCodeDepartmentImpl generateInternalCodeDepartment;
    @Mock
    private GenerateInternalCodeDepartmentRepositoryCustom generateInternalCodeDepartmentRepositoryCustom;

    private Department department1;
    private Department department2;
    private Department department3;
    private static final BigInteger INTERNAL_CODE_ONE = BigInteger.valueOf(1);
    private static final BigInteger INTERNAL_CODE_TWO = BigInteger.valueOf(2);
    private static final BigInteger INTERNAL_CODE_THREE = BigInteger.valueOf(3);

    private void startDepartment() {
        var departmentTest1 = new DepartmentTest();
        var departmentTest2 = new DepartmentTest();
        var departmentTest3 = new DepartmentTest();
        department1 = departmentTest1.generateDepartment();
        department2 = departmentTest2.generateDepartment();
        department3 = departmentTest3.generateDepartment();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startDepartment();
    }

    @Test
    void shouldGenerateANewCodeWhenSave() {
        when(generateInternalCodeDepartmentRepositoryCustom.generateInternalCode(department1))
                .thenReturn(INTERNAL_CODE_ONE);
        when(generateInternalCodeDepartmentRepositoryCustom.generateInternalCode(department2))
                .thenReturn(INTERNAL_CODE_TWO);
        when(generateInternalCodeDepartmentRepositoryCustom.generateInternalCode(department3))
                .thenReturn(INTERNAL_CODE_THREE);
        var generateInternalCode = generateInternalCodeDepartment.generate(department3);

        assertEquals(INTERNAL_CODE_THREE, generateInternalCode);
    }
}