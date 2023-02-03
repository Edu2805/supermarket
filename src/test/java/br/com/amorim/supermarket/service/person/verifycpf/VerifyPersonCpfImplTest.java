package br.com.amorim.supermarket.service.person.verifycpf;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.verifycpfrepositorycustom.VerifyCpfRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class VerifyPersonCpfImplTest {

    @InjectMocks
    private VerifyPersonCpfImpl verifyPersonCpf;
    @Mock
    private VerifyCpfRepositoryCustom verifyCpfRepositoryCustomMock;

    private Person person;

    private void startPerson() {
        PersonTest personTest = new PersonTest();
        person = personTest.generatePerson();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startPerson();
    }

    @Test
    void shouldReturnFalseWhenPersonCpfNotExistsBeforeSave() {
        when(verifyCpfRepositoryCustomMock.isCpfAlreadyExistsInTheDatabase(person))
                .thenReturn(false);
        var verifyCpf = verifyPersonCpf.verifyPersonCpfBeforeSave(person);

        assertFalse(verifyCpf);
    }

    @Test
    void shouldBusinessRuleExceptionWhenPersonCpfAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PERSON_CPF_ALREADY_EXISTS_WHEN_SAVE.message);

        when(verifyCpfRepositoryCustomMock.isCpfAlreadyExistsInTheDatabase(person))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonCpf.verifyPersonCpfBeforeSave(person);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonCpf.verifyPersonCpfBeforeSave(person));
    }

    @Test
    void verifyPersonCpfBeforeUpdate() {
    }
}