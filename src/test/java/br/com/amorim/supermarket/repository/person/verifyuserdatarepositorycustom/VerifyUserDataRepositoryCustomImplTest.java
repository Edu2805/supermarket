package br.com.amorim.supermarket.repository.person.verifyuserdatarepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource("classpath:application.properties")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyUserDataRepositoryCustomImplTest {

    @Autowired
    private VerifyUserDataRepositoryCustomImpl verifyUserDataRepositoryCustom;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepositoryUtils;
    @Autowired
    private PersonRepository personRepository;
    private Person person1;
    private Person person2;
    private UserData userData1;
    private UserData userData2;

    private void startPerson () {
        person1 = new Person();
        person2 = new Person();
        userData1 = new UserData();
        userData2 = new UserData();

        userData1 = generateEntitiesRepositoryUtils.generateUserData();
        userData2 = generateEntitiesRepositoryUtils.generateUserData();
        person1 = generateEntitiesRepositoryUtils.generatePerson(userData1);
        person2 = generateEntitiesRepositoryUtils.generatePerson(userData2);
    }

    private void deletePerson () {
        personRepository.delete(person1);
    }

    @BeforeEach
    void setUp() {
        startPerson();
    }

    @AfterEach
    void cleanUp() {
        deletePerson();
    }

    @Test
    void shouldReturnTrueWhenUserDataAlreadyExistsInDatabase() {
        var verifyUserData = verifyUserDataRepositoryCustom
                .isUserDataAlreadyExistsInTheDatabase(person1);
        assertTrue(verifyUserData);
    }

    @Test
    void shouldReturnFalseWhenUserDataNotExistsInDatabase() {
        person2.getUserData().setId(UUID.randomUUID());
        var verifyUserData = verifyUserDataRepositoryCustom
                .isUserDataAlreadyExistsInTheDatabase(person2);
        assertFalse(verifyUserData);
    }
}