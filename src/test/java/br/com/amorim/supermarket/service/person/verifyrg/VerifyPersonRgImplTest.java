package br.com.amorim.supermarket.service.person.verifyrg;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.person.verifyrgrepositorycustom.VerifyRgRepositoryCustomImpl;
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
class VerifyPersonRgImplTest {

    @InjectMocks
    private VerifyPersonRgImpl verifyPersonRg;
    @Mock
    private VerifyRgRepositoryCustomImpl verifyRgRepositoryCustomMock;
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
    void shouldReturnFalseWhenPersonRgNotExistsBeforeSave() {
        when(verifyRgRepositoryCustomMock.isRgAlreadyExistsInTheDatabase(person1))
                .thenReturn(false);
        var verifyRg = verifyPersonRg.verifyPersonRgBeforeSave(person1);

        assertFalse(verifyRg);
    }

    @Test
    void shouldBusinessRuleExceptionWhenPersonRgAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PERSON_RG_ALREADY_EXISTS_WHEN_SAVE.message);

        when(verifyRgRepositoryCustomMock.isRgAlreadyExistsInTheDatabase(person1))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonRg.verifyPersonRgBeforeSave(person1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonRg.verifyPersonRgBeforeSave(person1));
    }

    @Test
    void shouldReturnFalseWhenRgPersonNotExistsBeforeUpdate() {
        List<Person> peopleTestList = new ArrayList<>();
        peopleTestList.add(person1);
        peopleTestList.add(person2);

        when(personRepositoryMock.findAll()).thenReturn(peopleTestList);

        var verifyRgPerson = verifyPersonRg.verifyPersonRgBeforeUpdate(person1);

        assertFalse(verifyRgPerson);
    }

    @Test
    void shouldReturnABusinessRuleExceptionWhenRgPersonAlreadyExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType.PERSON_RG_ALREADY_EXISTS_WHEN_UPDATE.message);

        List<Person> peopleTestList = new ArrayList<>();
        person1.setRg(person2.getRg());
        peopleTestList.add(person1);
        peopleTestList.add(person2);

        when(personRepositoryMock.findAll()).thenReturn(peopleTestList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonRg.verifyPersonRgBeforeUpdate(person1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonRg.verifyPersonRgBeforeUpdate(person1));
    }
}