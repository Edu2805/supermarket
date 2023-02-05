package br.com.amorim.supermarket.service.person.verifyuserdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.person.verifyuserdatarepositorycustom.VerifyUserDataRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
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
class VerifyPersonUserDataImplTest {

    @InjectMocks
    private VerifyPersonUserDataImpl verifyPersonUserData;
    @Mock
    private VerifyUserDataRepositoryCustom verifyUserDataRepositoryCustomMock;
    @Mock
    private PersonRepository personRepositoryMock;

    private Person person1;
    private Person person2;

    private void startPerson() {
        PersonTest personTest1 = new PersonTest();
        PersonTest personTest2 = new PersonTest();

        person1 = personTest1.generatePerson();
        person2 = personTest2.generatePerson();

    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startPerson();
    }

    @Test
    void shouldReturnFalseWhenPersonUserDataNotExistsBeforeSave() {
        when(verifyUserDataRepositoryCustomMock.isUserDataAlreadyExistsInTheDatabase(person1))
                .thenReturn(false);
        var verifyUserData = verifyPersonUserData.verifyPersonUserDataBeforeSave(person1);

        assertFalse(verifyUserData);
    }

    @Test
    void shouldBusinessRuleExceptionWhenPersonUserDataAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PERSON_USER_DATA_ALREADY_EXISTS_WHEN_SAVE.message);

        when(verifyUserDataRepositoryCustomMock.isUserDataAlreadyExistsInTheDatabase(person1))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonUserData.verifyPersonUserDataBeforeSave(person1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonUserData.verifyPersonUserDataBeforeSave(person1));
    }

    @Test
    void shouldReturnFalseWhenPersonUserDataNotExistsBeforeUpdate() {
        List<Person> peopleTestList = new ArrayList<>();
        peopleTestList.add(person1);
        peopleTestList.add(person2);

        when(personRepositoryMock.findAll()).thenReturn(peopleTestList);

        var verifyUserDataPerson = verifyPersonUserData.verifyPersonUserDataBeforeUpdate(person1);

        assertFalse(verifyUserDataPerson);
    }

    @Test
    void shouldReturnABusinessRuleExceptionWhenPersonUserDataAlreadyExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType.PERSON_USER_DATA_ALREADY_EXISTS_WHEN_UPDATE.message);

        List<Person> peopleTestList = new ArrayList<>();
        person1.setUserData(person2.getUserData());
        peopleTestList.add(person1);
        peopleTestList.add(person2);

        when(personRepositoryMock.findAll()).thenReturn(peopleTestList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonUserData.verifyPersonUserDataBeforeUpdate(person1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonUserData.verifyPersonUserDataBeforeUpdate(person1));
    }
}