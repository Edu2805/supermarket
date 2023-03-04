package br.com.amorim.supermarket.repository.person.verifyuserdatarepositorycustom;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class VerifyUserDataRepositoryCustomImplTest {

    @Autowired
    private VerifyUserDataRepositoryCustomImpl verifyUserDataRepositoryCustom;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepositoryUtils;
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

    @BeforeEach
    void setUp() {
        startPerson();
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