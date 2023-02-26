package br.com.amorim.supermarket.service.department;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.service.department.generateinternalcode.GenerateInternalCodeDepartment;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.department.DepartmentTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigInteger;
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

class DepartmentCrudServiceImplTest {

    @InjectMocks
    private DepartmentCrudServiceImpl departmentCrudService;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private GenerateInternalCodeDepartment generateInternalCodeDepartment;

    private Department department;
    public static final String MESSAGE_ERROR = getString(MessagesKeyType.DEPARTMENT_NOT_FOUND.message);
    public static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

    private void startDepartment() {
        var departmentTest = new DepartmentTest();
        department = departmentTest.generateDepartment();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startDepartment();
    }

    @Test
    void shouldFindByIdWithSuccess() {
        when(departmentRepository.findById(department.getId()))
                .thenReturn(Optional.of(department));

        var findEstablishment = departmentCrudService.findById(department.getId());

        assertNotNull(findEstablishment);
        assertEquals(department.getId(), findEstablishment.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdIsUnknown() {
        when(departmentRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(department));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    departmentCrudService.findById(department.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> departmentCrudService
                .findById(department.getId()));

    }

    @Test
    void shouldSaveWithSuccess() {
        when(generateInternalCodeDepartment.generate(department))
                .thenReturn(BigInteger.ONE);
        when(departmentRepository.save(department)).thenReturn(department);
        var saveEstablishment = departmentCrudService.save(department);
        assertNotNull(saveEstablishment);
        assertEquals(Department.class, department.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);
        when(departmentRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(department)).thenReturn(department);

        departmentCrudService.update(department, department.getId());

        assertEquals(department.getId(), knownIdCapture.getValue());
        assertEquals(department, departmentRepository.save(department));
        assertEquals(department.getCode(),
                departmentRepository.save(department).getCode());
        assertEquals(department.getClass(),
                departmentRepository.save(department).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(departmentRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(department));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    departmentCrudService.update(department, department.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> departmentCrudService
                .update(department, department.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(departmentRepository.findById(department.getId()))
                .thenReturn(Optional.of(department));
        doNothing().when(departmentRepository).delete(department);

        departmentCrudService.delete(department.getId());

        verify(departmentRepository, times(1))
                .delete(department);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(departmentRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(department));
        doNothing().when(departmentRepository).delete(department);

        departmentCrudService.delete(department.getId());

        assertEquals(department.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(departmentRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(department));
        doNothing().when(departmentRepository).delete(department);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    departmentCrudService.delete(department.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                departmentCrudService.delete(department.getId()));
        verify(departmentRepository, times(0)).delete(department);
    }
}