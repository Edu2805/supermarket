package br.com.amorim.supermarket.service.jobposition;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.repository.jobposition.JobPositionRepository;
import br.com.amorim.supermarket.service.jobposition.filljobpositionname.fillname.FillPositionNameBySalary;
import br.com.amorim.supermarket.service.jobposition.filljobpositionname.verifyjobpositionname.VerifyJobPositionName;
import br.com.amorim.supermarket.service.jobposition.generateinternalcode.GenerateInternalCodeJobPosition;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.jobposition.JobPositionTest;
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

class JobPositionCrudServiceImplTest {

    @InjectMocks
    private JobPositionCrudServiceImpl jobPositionCrudService;
    @Mock
    private JobPositionRepository jobPositionRepository;
    @Mock
    private GenerateInternalCodeJobPosition generateInternalCodeJobPosition;
    @Mock
    private VerifyJobPositionName verifyJobPositionName;
    @Mock
    private FillPositionNameBySalary fillPositionNameBySalary;

    private JobPosition jobPosition1;
    public static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
    public static final String MESSAGE_ERROR = getString(MessagesKeyType.JOB_POSITION_NOT_FOUND.message);

    private void startJobPosition() {
        var jobPositionTest1 = new JobPositionTest();
        jobPosition1 = jobPositionTest1.generateJobPosition();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startJobPosition();
    }

    @Test
    void shouldFindById() {
        when(jobPositionRepository.findById(jobPosition1.getId()))
                .thenReturn(Optional.of(jobPosition1));
        var findByIdJobPosition = jobPositionCrudService.findById(jobPosition1.getId());

        assertNotNull(findByIdJobPosition);
        assertEquals(jobPosition1.getId(), findByIdJobPosition.getId());
    }

    @Test
    void shouldNotFindById() {
        when(jobPositionRepository.findById(jobPosition1.getId()))
                .thenReturn(Optional.empty());
        var exceptionMessage = assertThrows(NotFoundException.class, () ->
                jobPositionCrudService.findById(jobPosition1.getId())
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
    }

    @Test
    void shouldSaveWithSuccess() {
        when(jobPositionRepository.save(jobPosition1))
                .thenReturn(jobPosition1);
        var saveJobPosition = jobPositionCrudService.save(jobPosition1);
        assertNotNull(saveJobPosition);
        assertEquals(JobPosition.class, saveJobPosition.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);
        when(jobPositionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(jobPosition1));
        when(jobPositionRepository.save(jobPosition1))
                .thenReturn(jobPosition1);

        jobPositionCrudService.update(jobPosition1, jobPosition1.getId());

        assertEquals(jobPosition1.getId(), knownIdCapture.getValue());
        assertEquals(jobPosition1, jobPositionRepository.save(jobPosition1));
        assertEquals(jobPosition1.getCode(),
                jobPositionRepository.save(jobPosition1).getCode());
        assertEquals(jobPosition1.getClass(),
                jobPositionRepository.save(jobPosition1).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(jobPositionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(jobPosition1));

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    jobPositionCrudService.update(jobPosition1, jobPosition1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> jobPositionCrudService
                .update(jobPosition1, jobPosition1.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(jobPositionRepository.findById(jobPosition1.getId()))
                .thenReturn(Optional.of(jobPosition1));
        doNothing().when(jobPositionRepository).delete(jobPosition1);

        jobPositionCrudService.delete(jobPosition1.getId());

        verify(jobPositionRepository, times(1))
                .delete(jobPosition1);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(jobPositionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(jobPosition1));
        doNothing().when(jobPositionRepository).delete(jobPosition1);

        jobPositionCrudService.delete(jobPosition1.getId());

        assertEquals(jobPosition1.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(jobPositionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(jobPosition1));
        doNothing().when(jobPositionRepository).delete(jobPosition1);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    jobPositionCrudService.delete(jobPosition1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                jobPositionCrudService.delete(jobPosition1.getId()));
        verify(jobPositionRepository, times(0)).delete(jobPosition1);
    }
}