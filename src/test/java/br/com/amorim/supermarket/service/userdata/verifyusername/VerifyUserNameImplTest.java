package br.com.amorim.supermarket.service.userdata.verifyusername;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.repository.userdata.userdatarepositorycustom.UserDataRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata.UserDataTest;
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
class VerifyUserNameImplTest {

    @InjectMocks
    private VerifyUserNameImpl verifyUserName;
    @Mock
    private UserDataRepositoryCustom userDataRepositoryCustom;
    @Mock
    private UserDataRepository userDataRepositoryMock;

    private UserData userData1;
    private UserData userData2;

    private void startUserData() {
        UserDataTest userDataTest1 = new UserDataTest();
        UserDataTest userDataTest2 = new UserDataTest();

        userData1 = userDataTest1.generateUserData();
        userData2 = userDataTest2.generateUserData();

    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startUserData();
    }

    @Test
    void shouldReturnFalseWhenUserNameNotExistsBeforeSave() {
        when(userDataRepositoryCustom.isUserNameAlreadyExistsInTheDatabase(userData1))
                .thenReturn(false);
        var verifyUserNameBeforeSave = verifyUserName.verifyUserDataBeforeSave(userData1);

        assertFalse(verifyUserNameBeforeSave);
    }

    @Test
    void shouldBusinessRuleExceptionWhenUserNameAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .USER_DATA_USER_NAME_ALREADY_EXISTS_WHEN_SAVE.message);

        when(userDataRepositoryCustom.isUserNameAlreadyExistsInTheDatabase(userData1))
                .thenReturn(true);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyUserName.verifyUserDataBeforeSave(userData1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyUserName.verifyUserDataBeforeSave(userData1));
    }

    @Test
    void shouldReturnFalseWhenUserNameNotExistsBeforeUpdate() {
        List<UserData> userDataList = new ArrayList<>();
        userDataList.add(userData1);
        userDataList.add(userData2);

        when(userDataRepositoryMock.findAll()).thenReturn(userDataList);

        var verifyRgPerson = verifyUserName.verifyUserDataBeforeUpdate(userData1);

        assertFalse(verifyRgPerson);
    }

    @Test
    void shouldReturnABusinessRuleExceptionWhenUserNameAlreadyExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType
                .USER_DATA_USER_NAME_ALREADY_EXISTS_WHEN_UPDATE.message);

        List<UserData> userDataList = new ArrayList<>();
        userData1.setUserName(userData2.getUserName());
        userDataList.add(userData1);
        userDataList.add(userData2);

        when(userDataRepositoryMock.findAll()).thenReturn(userDataList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyUserName.verifyUserDataBeforeUpdate(userData1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyUserName.verifyUserDataBeforeUpdate(userData1));
    }

}