package br.com.amorim.supermarket.repository.person.verifyrgrepositorycustom;

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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource("classpath:application.properties")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyRgRepositoryCustomImplTest {

    @Autowired
    private VerifyRgRepositoryCustomImpl verifyRgRepositoryCustom;
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
        personRepository.delete(person2);
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
    void shouldReturnTrueWhenRgAlreadyExistsInDatabase() {
        var verifyRg = verifyRgRepositoryCustom
                .isRgAlreadyExistsInTheDatabase(person1);
        assertTrue(verifyRg);
    }

    @Test
    void shouldReturnFalseWhenRgNotExistsInDatabase() {
        person2.setRg("9876-A");
        var verifyRg = verifyRgRepositoryCustom
                .isRgAlreadyExistsInTheDatabase(person2);
        assertFalse(verifyRg);
    }
}