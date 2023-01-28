package br.com.amorim.supermarket.service.establishment.verifycnpjestablishment;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom.VerifyCnpjEstablishmentRepositoryCustom;
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
class VerifyCnpjEstablishmentImplTest {

    @InjectMocks
    private VerifyCnpjEstablishmentImpl verifyCnpjEstablishment;
    @Mock
    private VerifyCnpjEstablishmentRepositoryCustom verifyCnpjEstablishmentRepositoryCustomMock;
    @Mock
    private EstablishmentRepository establishmentRepositoryMock;

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
    void shouldReturnFalseIfCnpjNotExistsBeforeSave() {
        when(verifyCnpjEstablishmentRepositoryCustomMock.existsByCnpj(establishment1))
                .thenReturn(false);
        var verifyCnpj = verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeSave(establishment1);

        assertFalse(verifyCnpj);
    }

    @Test
    void shouldReturnABusinessRuleExceptionIfCnpjAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType.ESTABLISHMENT_CNPJ_ALREADY_EXISTS.message);

        when(verifyCnpjEstablishmentRepositoryCustomMock.existsByCnpj(establishment1))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeSave(establishment1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeSave(establishment1));
    }

    @Test
    void shouldReturnFalseIfCnpjNotExistsBeforeUpdate() {
        List<Establishment> establishmentTestList = new ArrayList<>();
        establishmentTestList.add(establishment1);
        establishmentTestList.add(establishment2);

        when(establishmentRepositoryMock.findAll()).thenReturn(establishmentTestList);

        var verifyCnpj = verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeUpdate(establishment1);

        assertFalse(verifyCnpj);
    }

    @Test
    void shouldReturnABusinessRuleExceptionIfCnpjAlreadyExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType.ESTABLISHMENT_CNPJ_ALREADY_EXISTS.message);

        List<Establishment> establishmentTestList = new ArrayList<>();
        establishment1.setCnpj(establishment2.getCnpj());
        establishmentTestList.add(establishment1);
        establishmentTestList.add(establishment2);

        when(establishmentRepositoryMock.findAll()).thenReturn(establishmentTestList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeUpdate(establishment1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeUpdate(establishment1));
    }
}