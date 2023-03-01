package br.com.amorim.supermarket.service.userdata.setusernameinperson;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata.UserDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserNameInPersonImplTest {

    @InjectMocks
    private UserNameInPersonImpl userNameInPerson;
    @Mock
    private PersonRepository personRepository;

    private UserData userData;
    private Person person;

    private void startUserData() {
        var userDataTest = new UserDataTest();
        var personTest = new PersonTest();
        person = personTest.generatePerson();
        userData = userDataTest.generateUserData();
    }
    @BeforeEach
    void setUp() {
        openMocks(this);
        startUserData();
    }

    @Test
    void shouldSetUserNameInPerson() {
        when(personRepository.findByUserData(userData))
                .thenReturn(Optional.of(person));
        when(personRepository.findById(person.getId()))
                .thenReturn(Optional.of(person));
        when(personRepository.save(person))
                .thenReturn(person);

        userNameInPerson.setUserNameInPerson(userData);

        assertEquals(userData.getUserName(), person.getEmail());
    }
}