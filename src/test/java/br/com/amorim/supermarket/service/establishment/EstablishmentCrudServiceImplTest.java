package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.service.establishment.generateinternalcode.GenerateInternalCodeEstablishment;
import br.com.amorim.supermarket.service.establishment.verifycnpjestablishment.VerifyCnpjEstablishment;
import br.com.amorim.supermarket.service.establishment.verifymunicipalorstateregistration.VerifyMunicipalOrStateRegistrationEstablishment;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.establishment.EstablishmentTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
class EstablishmentCrudServiceImplTest {

    @InjectMocks
    private EstablishmentCrudServiceImpl establishmentCrudService;
    @Mock
    private EstablishmentRepository establishmentRepositoryMock;
    @Mock
    private GenerateInternalCodeEstablishment generateInternalCodeEstablishmentMock;
    @Mock
    private VerifyMunicipalOrStateRegistrationEstablishment verifyMunicipalRegistrationMock;
    @Mock
    private VerifyCnpjEstablishment verifyCnpjEstablishmentMock;

    private Establishment establishment1;
    public static final String MESSAGE_ERROR = getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message);
    public static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

    private void startEstablishment() {
        EstablishmentTest establishmentTest1 = new EstablishmentTest();
        establishment1 = establishmentTest1.generateEstablishment();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startEstablishment();
    }

    @Test
    void shouldFindByIdWithSuccess() {
        when(establishmentRepositoryMock.findById(establishment1.getId()))
                .thenReturn(Optional.of(establishment1));

        var findEstablishment = establishmentCrudService
                .findById(establishment1.getId());

        assertNotNull(findEstablishment);
        assertEquals(establishment1.getId(), findEstablishment.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdIs() {
        when(establishmentRepositoryMock.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(establishment1));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    establishmentCrudService.findById(establishment1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> establishmentCrudService
                .findById(establishment1.getId()));

    }

    @Test
    void shouldSaveWithSuccess() {
        when(establishmentRepositoryMock.save(establishment1))
                .thenReturn(establishment1);
        var saveEstablishment = establishmentCrudService.save(establishment1);
        assertNotNull(saveEstablishment);
        assertEquals(Establishment.class, establishment1.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);
        when(establishmentRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(establishment1));
        when(establishmentRepositoryMock.save(establishment1))
                .thenReturn(establishment1);

        establishmentCrudService.update(establishment1, establishment1.getId());

        assertEquals(establishment1.getId(), knownIdCapture.getValue());
        assertEquals(establishment1, establishmentRepositoryMock.save(establishment1));
        assertEquals(establishment1.getCode(),
                establishmentRepositoryMock.save(establishment1).getCode());
        assertEquals(establishment1.getClass(),
                establishmentRepositoryMock.save(establishment1).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(establishmentRepositoryMock.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(establishment1));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    establishmentCrudService.update(establishment1, establishment1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> establishmentCrudService
                .update(establishment1, establishment1.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(establishmentRepositoryMock.findById(establishment1.getId()))
                .thenReturn(Optional.of(establishment1));
        doNothing().when(establishmentRepositoryMock).delete(establishment1);

        establishmentCrudService.delete(establishment1.getId());

        verify(establishmentRepositoryMock, times(1))
                .delete(establishment1);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

        when(establishmentRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(establishment1));
        doNothing().when(establishmentRepositoryMock).delete(establishment1);

        establishmentCrudService.delete(establishment1.getId());

        assertEquals(establishment1.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(establishmentRepositoryMock.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(establishment1));
        doNothing().when(establishmentRepositoryMock).delete(establishment1);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    establishmentCrudService.delete(establishment1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                establishmentCrudService.delete(establishment1.getId()));
        verify(establishmentRepositoryMock, times(0)).delete(establishment1);
    }
}