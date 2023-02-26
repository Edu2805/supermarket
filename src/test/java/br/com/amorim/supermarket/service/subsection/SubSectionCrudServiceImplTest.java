package br.com.amorim.supermarket.service.subsection;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.service.subsection.generateinternalcode.GenerateInternalCodeSubSection;
import br.com.amorim.supermarket.service.subsection.verifysubsectionname.VerifySubSectionName;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
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


class SubSectionCrudServiceImplTest {

    @InjectMocks
    private SubSectionCrudServiceImpl subSectionCrudService;
    @Mock
    private SubSectionRepository subSectionRepository;
    @Mock
    private GenerateInternalCodeSubSection generateInternalCodeSubSection;
    @Mock
    private VerifySubSectionName verifySubSectionName;

    private static final String MESSAGE_ERROR = getString(MessagesKeyType.SUB_SECTION_NOT_FOUND.message);
    private static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
    private SubSection subSection1;

    private void startSubSection() {
        var subSectionTest1 = new SubSectionTest();
        subSection1 = subSectionTest1.generateSubsection();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSubSection();
    }


    @Test
    void shouldFindByIdWithSuccess() {
        when(subSectionRepository.findById(subSection1.getId()))
                .thenReturn(Optional.of(subSection1));

        var findEstablishment = subSectionCrudService.findById(subSection1.getId());

        assertNotNull(findEstablishment);
        assertEquals(subSection1.getId(), findEstablishment.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdIsUnknown() {
        when(subSectionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(subSection1));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    subSectionCrudService.findById(subSection1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> subSectionCrudService
                .findById(subSection1.getId()));

    }

    @Test
    void shouldSaveWithSuccess() {
        when(verifySubSectionName.existsBySubSectionNameBeforeSave(subSection1))
                .thenReturn(false);
        when(generateInternalCodeSubSection.generate(subSection1))
                .thenReturn(BigInteger.ONE);
        when(subSectionRepository.save(subSection1)).thenReturn(subSection1);
        var saveEstablishment = subSectionCrudService.save(subSection1);
        assertNotNull(saveEstablishment);
        assertEquals(SubSection.class, subSection1.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);
        when(subSectionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(subSection1));
        when(verifySubSectionName.existsBySubSectionNameBeforeUpdate(subSection1))
                .thenReturn(false);
        when(subSectionRepository.save(subSection1)).thenReturn(subSection1);

        subSectionCrudService.update(subSection1, subSection1.getId());

        assertEquals(subSection1.getId(), knownIdCapture.getValue());
        assertEquals(subSection1, subSectionRepository.save(subSection1));
        assertEquals(subSection1.getCode(),
                subSectionRepository.save(subSection1).getCode());
        assertEquals(subSection1.getClass(),
                subSectionRepository.save(subSection1).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(subSectionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(subSection1));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    subSectionCrudService.update(subSection1, subSection1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> subSectionCrudService
                .update(subSection1, subSection1.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(subSectionRepository.findById(subSection1.getId()))
                .thenReturn(Optional.of(subSection1));
        doNothing().when(subSectionRepository).delete(subSection1);

        subSectionCrudService.delete(subSection1.getId());

        verify(subSectionRepository, times(1))
                .delete(subSection1);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(subSectionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(subSection1));
        doNothing().when(subSectionRepository).delete(subSection1);

        subSectionCrudService.delete(subSection1.getId());

        assertEquals(subSection1.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(subSectionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(subSection1));
        doNothing().when(subSectionRepository).delete(subSection1);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    subSectionCrudService.delete(subSection1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                subSectionCrudService.delete(subSection1.getId()));
        verify(subSectionRepository, times(0)).delete(subSection1);
    }
}