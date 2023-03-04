package br.com.amorim.supermarket.repository.userdata.verifyusernamerepositorycustom;

import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class VerifyUserNameRepositoryCustomImplTest {

    @Autowired
    private VerifyUserNameRepositoryCustomImpl verifyUserNameRepositoryCustom;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepositoryUtils;

    private UserData userData1;
    private UserData userData2;

    private void startUserData() {
        userData1 = new UserData();
        userData2 = new UserData();

        userData1 = generateEntitiesRepositoryUtils.generateUserData();
        userData2 = generateEntitiesRepositoryUtils.generateUserData();
    }

    @BeforeEach
    void setUp() {
        startUserData();
    }

    @Test
    void shouldReturnTrueWhenUserNameAlreadyExistsInDatabase() {
        var verifyUserName = verifyUserNameRepositoryCustom
                .isUserNameAlreadyExistsInTheDatabase(userData1);
        assertTrue(verifyUserName);
    }

    @Test
    void shouldReturnFalseWhenUserNameNotExistsInDatabase() {
        userData2.setUserName("test@test.com");
        var verifyUserName = verifyUserNameRepositoryCustom
                .isUserNameAlreadyExistsInTheDatabase(userData2);
        assertFalse(verifyUserName);
    }
}