package br.com.amorim.supermarket.service.otheraddition;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.repository.otheraddition.OtherAdditionRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otheraddition.OtherAdditionTest;
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

class OtherAdditionCrudServiceImplTest {

    @InjectMocks
    private OtherAdditionCrudServiceImpl otherAdditionCrudService;
    @Mock
    private OtherAdditionRepository otherAdditionRepository;

    private OtherAddition otherAddition;
    ArgumentCaptor<UUID> knownIdCapture;
    public static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
    public static final String MESSAGE_ERROR = getString(MessagesKeyType.OTHER_ADDITION_NOT_FOUND.message);

    private void startOtherAddition() {
        var otherAdditionTest = new OtherAdditionTest();
        otherAddition = otherAdditionTest.generateOtherAddition();
        knownIdCapture = ArgumentCaptor.forClass(UUID.class);
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startOtherAddition();
    }

    @Test
    void shouldFindById() {
        when(otherAdditionRepository.findById(otherAddition.getId()))
                .thenReturn(Optional.of(otherAddition));
        var findByIdOtherAddition = otherAdditionCrudService.findById(otherAddition.getId());

        assertNotNull(findByIdOtherAddition);
        assertEquals(otherAddition.getId(), findByIdOtherAddition.getId());
    }

    @Test
    void shouldNotFindById() {
        when(otherAdditionRepository.findById(otherAddition.getId()))
                .thenReturn(Optional.empty());
        var exceptionMessage = assertThrows(NotFoundException.class, () ->
                otherAdditionCrudService.findById(otherAddition.getId())
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
    }

    @Test
    void shouldSaveWithSuccess() {
        when(otherAdditionRepository.save(otherAddition))
                .thenReturn(otherAddition);
        var saveOtherAddition = otherAdditionCrudService.save(otherAddition);
        assertNotNull(saveOtherAddition);
        assertEquals(OtherAddition.class, saveOtherAddition.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        when(otherAdditionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(otherAddition));
        when(otherAdditionRepository.save(otherAddition))
                .thenReturn(otherAddition);

        otherAdditionCrudService.update(otherAddition, otherAddition.getId());

        assertEquals(otherAddition.getId(), knownIdCapture.getValue());
        assertEquals(otherAddition, otherAdditionRepository.save(otherAddition));
        assertEquals(otherAddition.getClass(),
                otherAdditionRepository.save(otherAddition).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(otherAdditionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(otherAddition));

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    otherAdditionCrudService.update(otherAddition, otherAddition.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> otherAdditionCrudService
                .update(otherAddition, otherAddition.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(otherAdditionRepository.findById(otherAddition.getId()))
                .thenReturn(Optional.of(otherAddition));
        doNothing().when(otherAdditionRepository).delete(otherAddition);

        otherAdditionCrudService.delete(otherAddition.getId());

        verify(otherAdditionRepository, times(1))
                .delete(otherAddition);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        when(otherAdditionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(otherAddition));
        doNothing().when(otherAdditionRepository).delete(otherAddition);

        otherAdditionCrudService.delete(otherAddition.getId());

        assertEquals(otherAddition.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(otherAdditionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(otherAddition));
        doNothing().when(otherAdditionRepository).delete(otherAddition);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    otherAdditionCrudService.delete(otherAddition.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                otherAdditionCrudService.delete(otherAddition.getId()));
        verify(otherAdditionRepository, times(0)).delete(otherAddition);
    }
}