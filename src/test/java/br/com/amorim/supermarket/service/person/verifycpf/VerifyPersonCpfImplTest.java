package br.com.amorim.supermarket.service.person.verifycpf;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.person.verifycpfrepositorycustom.VerifyCpfRepositoryCustom;
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
class VerifyPersonCpfImplTest {

    @InjectMocks
    private VerifyPersonCpfImpl verifyPersonCpf;
    @Mock
    private VerifyCpfRepositoryCustom verifyCpfRepositoryCustomMock;
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
    void shouldReturnFalseWhenPersonCpfNotExistsBeforeSave() {
        when(verifyCpfRepositoryCustomMock.isCpfAlreadyExistsInTheDatabase(person1))
                .thenReturn(false);
        var verifyCpf = verifyPersonCpf.verifyPersonCpfBeforeSave(person1);

        assertFalse(verifyCpf);
    }

    @Test
    void shouldBusinessRuleExceptionWhenPersonCpfAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PERSON_CPF_ALREADY_EXISTS_WHEN_SAVE.message);

        when(verifyCpfRepositoryCustomMock.isCpfAlreadyExistsInTheDatabase(person1))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonCpf.verifyPersonCpfBeforeSave(person1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonCpf.verifyPersonCpfBeforeSave(person1));
    }

    @Test
    void shouldReturnFalseWhenCpfPersonNotExistsBeforeUpdate() {
        List<Person> personTestList = new ArrayList<>();
        personTestList.add(person1);
        personTestList.add(person2);

        when(personRepositoryMock.findAll()).thenReturn(personTestList);

        var verifyCnpj = verifyPersonCpf.verifyPersonCpfBeforeUpdate(person1);

        assertFalse(verifyCnpj);
    }

    @Test
    void shouldReturnABusinessRuleExceptionWhenCpfPersonAlreadyExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType.PERSON_CPF_ALREADY_EXISTS.message);

        List<Person> personTestList = new ArrayList<>();
        person1.setCpf(person2.getCpf());
        personTestList.add(person1);
        personTestList.add(person2);

        when(personRepositoryMock.findAll()).thenReturn(personTestList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyPersonCpf.verifyPersonCpfBeforeUpdate(person1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyPersonCpf.verifyPersonCpfBeforeUpdate(person1));
    }

}