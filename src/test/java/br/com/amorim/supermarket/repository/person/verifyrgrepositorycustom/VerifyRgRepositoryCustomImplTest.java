package br.com.amorim.supermarket.repository.person.verifyrgrepositorycustom;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class VerifyRgRepositoryCustomImplTest {

    @Autowired
    private VerifyRgRepositoryCustomImpl verifyRgRepositoryCustom;
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