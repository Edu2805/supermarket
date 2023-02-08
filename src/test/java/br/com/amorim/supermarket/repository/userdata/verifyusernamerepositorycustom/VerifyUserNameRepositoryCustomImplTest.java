package br.com.amorim.supermarket.repository.userdata.verifyusernamerepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyUserNameRepositoryCustomImplTest {

    @Autowired
    private VerifyUserNameRepositoryCustomImpl verifyUserNameRepositoryCustom;
    @Autowired
    private UserDataRepository userDataRepository;
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

    private void deleteUserData() {
        userDataRepository.delete(userData1);
        userDataRepository.delete(userData2);
    }

    @BeforeEach
    void setUp() {
        startUserData();
    }

    @AfterEach
    void cleanUp() {
        deleteUserData();
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