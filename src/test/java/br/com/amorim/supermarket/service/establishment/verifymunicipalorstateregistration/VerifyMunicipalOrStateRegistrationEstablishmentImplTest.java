package br.com.amorim.supermarket.service.establishment.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.establishment.verifymunicipalorstateregistrationrepositorycustom.VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.establishment.EstablishmentTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class VerifyMunicipalOrStateRegistrationEstablishmentImplTest {

    @InjectMocks
    private VerifyMunicipalOrStateRegistrationEstablishmentImpl verifyMunicipalOrStateRegistrationEstablishment;
    @Mock
    private VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom verifyMunicipalOrStateRegistrationRepositoryCustom;
    @Mock
    private EstablishmentRepository establishmentRepository;

    private Establishment establishment1;
    private Establishment establishment2;

    private void startEstablishment() {
        EstablishmentTest establishmentTest1 = new EstablishmentTest();
        establishment1 = establishmentTest1.generateEstablishment();

        EstablishmentTest establishmentTest2 = new EstablishmentTest();
        establishment2 = establishmentTest2.generateEstablishment();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startEstablishment();
    }

    @Test
    void shouldReturnFalseIfMunicipalOrStateRegistrationNotExistsBeforeSave() {
        when(verifyMunicipalOrStateRegistrationRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment1))
                .thenReturn(3);

        var existsByMunicipalOrStateRegistration = verifyMunicipalOrStateRegistrationEstablishment
                .verifyMunicipalOrStateRegistrationBeforeSave(establishment1);

        assertFalse(existsByMunicipalOrStateRegistration);
    }

    @Test
    void shouldReturnBusinessRuleExceptionIfMunicipalRegistrationAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .ESTABLISHMENT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message);

        when(verifyMunicipalOrStateRegistrationRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment1))
                .thenReturn(1);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationEstablishment
                            .verifyMunicipalOrStateRegistrationBeforeSave(establishment1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationEstablishment
                        .verifyMunicipalOrStateRegistrationBeforeSave(establishment1));
    }

    @Test
    void shouldReturnBusinessRuleExceptionIfStateRegistrationAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .ESTABLISHMENT_STATE_REGISTER_ALREADY_EXISTS.message);

        when(verifyMunicipalOrStateRegistrationRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment1))
                .thenReturn(2);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationEstablishment
                            .verifyMunicipalOrStateRegistrationBeforeSave(establishment1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationEstablishment
                        .verifyMunicipalOrStateRegistrationBeforeSave(establishment1));
    }

    @Test
    void shouldReturnFalseWhenMunicipalOrStateRegistrationNotExistsBeforeUpdate() {
        List<Establishment> establishmentList = new ArrayList<>();
        establishmentList.add(establishment1);
        establishmentList.add(establishment2);

        when(establishmentRepository.findAll()).thenReturn(establishmentList);

        var verify = verifyMunicipalOrStateRegistrationEstablishment
                .verifyMunicipalOrStateRegistrationBeforeUpdate(establishment1);

        assertFalse(verify);
    }

    @Test
    void shouldReturnBusinessRuleExceptionWhenMunicipalRegistrationAlreadyExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType
                .ESTABLISHMENT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message);

        List<Establishment> establishmentList = new ArrayList<>();
        establishment1.setMunicipalRegistration(establishment2.getMunicipalRegistration());
        establishmentList.add(establishment1);
        establishmentList.add(establishment2);

        when(establishmentRepository.findAll()).thenReturn(establishmentList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationEstablishment
                            .verifyMunicipalOrStateRegistrationBeforeUpdate(establishment1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationEstablishment
                        .verifyMunicipalOrStateRegistrationBeforeUpdate(establishment1));
    }

    @Test
    void shouldReturnBusinessRuleExceptionWhenStateRegistrationAlreadyExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType
                .ESTABLISHMENT_STATE_REGISTER_ALREADY_EXISTS.message);

        List<Establishment> establishmentList = new ArrayList<>();
        establishment1.setStateRegistration(establishment2.getStateRegistration());
        establishmentList.add(establishment1);
        establishmentList.add(establishment2);

        when(establishmentRepository.findAll()).thenReturn(establishmentList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationEstablishment
                            .verifyMunicipalOrStateRegistrationBeforeUpdate(establishment1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationEstablishment
                        .verifyMunicipalOrStateRegistrationBeforeUpdate(establishment1));
    }
}