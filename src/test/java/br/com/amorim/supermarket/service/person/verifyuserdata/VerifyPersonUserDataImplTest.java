package br.com.amorim.supermarket.service.person.verifyuserdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.verifyuserdatarepositorycustom.VerifyUserDataRepositoryCustom;
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
class VerifyPersonUserDataImplTest {

    @InjectMocks
    private VerifyPersonUserDataImpl verifyPersonUserData;
    @Mock
    private VerifyUserDataRepositoryCustom verifyUserDataRepositoryCustomMock;

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
        when(verifyUserDataRepositoryCustomMock.isUserDataAlreadyExistsInTheDatabase(person))
                .thenReturn(false);
        var verifyCpf = verifyPersonUserData.verifyPersonUserDataBeforeSave(person);

        assertFalse(verifyCpf);
    }

    @Test
    void shouldBusinessRuleExceptionWhenPersonCpfAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PERSON_USER_DATA_ALREADY_EXISTS_WHEN_SAVE.message);

        when(verifyUserDataRepositoryCustomMock.isUserDataAlreadyExistsInTheDatabase(person))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonUserData.verifyPersonUserDataBeforeSave(person);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonUserData.verifyPersonUserDataBeforeSave(person));
    }

    @Test
    void verifyPersonUserDataBeforeUpdate() {
    }
}