package br.com.amorim.supermarket.service.mainsection;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.service.mainsection.generateinternalcode.GenerateInternalCodeMainSection;
import br.com.amorim.supermarket.service.mainsection.verifymainsectionname.VerifyMainSectionName;
import br.com.amorim.supermarket.service.mainsection.verifysubsectionbeforedelete.VerifySubSectionBeforeSelete;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.mainsection.MainSectionTest;
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

class MainSectionCrudServiceImplTest {

    @InjectMocks
    private MainSectionCrudServiceImpl mainSectionCrudService;
    @Mock
    private MainSectionRepository mainSectionRepository;
    @Mock
    private GenerateInternalCodeMainSection generateInternalCodeMainSection;
    @Mock
    private VerifyMainSectionName verifyMainSectionName;
    @Mock
    private VerifySubSectionBeforeSelete verifySubSectionBeforeSelete;

    private static final String MESSAGE_ERROR = getString(MessagesKeyType.MAIN_SECTION_NOT_FOUND.message);
    private static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
    private MainSection mainSection;

    private void startMainSection() {
        var mainSectionTest = new MainSectionTest();
        mainSection = mainSectionTest.generateMainsection();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startMainSection();
    }

    @Test
    void shouldFindByIdWithSuccess() {
        when(mainSectionRepository.findById(mainSection.getId()))
                .thenReturn(Optional.of(mainSection));

        var findEstablishment = mainSectionCrudService.findById(mainSection.getId());

        assertNotNull(findEstablishment);
        assertEquals(mainSection.getId(), findEstablishment.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdIsUnknown() {
        when(mainSectionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(mainSection));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    mainSectionCrudService.findById(mainSection.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> mainSectionCrudService
                .findById(mainSection.getId()));

    }

    @Test
    void shouldSaveWithSuccess() {
        when(verifyMainSectionName.existsByMainSectionNameBeforeSave(mainSection))
                .thenReturn(false);
        when(generateInternalCodeMainSection.generate(mainSection))
                .thenReturn(BigInteger.ONE);
        when(mainSectionRepository.save(mainSection)).thenReturn(mainSection);
        var saveEstablishment = mainSectionCrudService.save(mainSection);
        assertNotNull(saveEstablishment);
        assertEquals(MainSection.class, mainSection.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);
        when(mainSectionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(mainSection));
        when(verifyMainSectionName.existsByMainSectionNameBeforeUpdate(mainSection))
                .thenReturn(false);
        when(mainSectionRepository.save(mainSection)).thenReturn(mainSection);

        mainSectionCrudService.update(mainSection, mainSection.getId());

        assertEquals(mainSection.getId(), knownIdCapture.getValue());
        assertEquals(mainSection, mainSectionRepository.save(mainSection));
        assertEquals(mainSection.getCode(),
                mainSectionRepository.save(mainSection).getCode());
        assertEquals(mainSection.getClass(),
                mainSectionRepository.save(mainSection).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(mainSectionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(mainSection));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    mainSectionCrudService.update(mainSection, mainSection.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> mainSectionCrudService
                .update(mainSection, mainSection.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(mainSectionRepository.findById(mainSection.getId()))
                .thenReturn(Optional.of(mainSection));
        doNothing().when(mainSectionRepository).delete(mainSection);
        doNothing().when(verifySubSectionBeforeSelete).verifyMainSectionSubSection(mainSection);

        mainSectionCrudService.delete(mainSection.getId());

        verify(mainSectionRepository, times(1))
                .delete(mainSection);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(mainSectionRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(mainSection));
        doNothing().when(mainSectionRepository).delete(mainSection);
        doNothing().when(verifySubSectionBeforeSelete).verifyMainSectionSubSection(mainSection);

        mainSectionCrudService.delete(mainSection.getId());

        assertEquals(mainSection.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(mainSectionRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(mainSection));
        doNothing().when(mainSectionRepository).delete(mainSection);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    mainSectionCrudService.delete(mainSection.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                mainSectionCrudService.delete(mainSection.getId()));
        verify(mainSectionRepository, times(0)).delete(mainSection);
    }
}