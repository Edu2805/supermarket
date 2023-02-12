package br.com.amorim.supermarket.service.userdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.service.userdata.setisemployee.UserDataUpdateIsEmployee;
import br.com.amorim.supermarket.service.userdata.setusernameinperson.UserNameInPerson;
import br.com.amorim.supermarket.service.userdata.verifyusername.VerifyUserName;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata.UserDataTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserDataCrudServiceImplTest {

    @InjectMocks
    private UserDataCrudServiceImpl userDataCrudService;
    @Mock
    private UserDataRepository userDataRepositoryMock;
    @Mock
    private VerifyPageSize verifyPageSizeMock;
    @Mock
    private VerifyUserName verifyUserNameMock;
    @Mock
    private UserNameInPerson userNameInPersonMock;
    @Mock
    private UserDataUpdateIsEmployee userDataUpdateIsEmployeeMock;

    private final ArgumentCaptor<UUID> knownIdCapture = ArgumentCaptor.forClass(UUID.class);

    private UserData userData;

    private void startUser() {
        UserDataTest userDataTest = new UserDataTest();
        userData = userDataTest.generateUserData();
    }

    @BeforeEach
    void setUp() {
        startUser();
    }

    @Test
    void shouldFindByIdWithSuccess() {
        var knownId = userData.getId();
        when(userDataRepositoryMock.findById(knownId))
                .thenReturn(Optional.of(userData));

        var userDataFound = userDataCrudService.findById(knownId);

        assertEquals(knownId, userDataFound.getId());
    }

    @Test
    void shouldShowANotFoundExceptionMessageWhenFindByIdWithIncorrectId() {
        String messageError = getString(MessagesKeyType.USER_DATA_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";

        when(userDataRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(userData));

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    userDataCrudService.findById(userData.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                userDataCrudService.findById(userData.getId()));
    }

    @Test
    void shouldSaveUserDataWithSuccess() {
        when(verifyUserNameMock.verifyUserDataBeforeSave(userData))
                .thenReturn(false);
        when(userDataRepositoryMock.save(userData))
                .thenReturn(userData);

        var verifyUserDataBeforeSave = userDataCrudService.save(userData);

        assertNotNull(verifyUserDataBeforeSave);
        assertEquals(UserData.class, verifyUserDataBeforeSave.getClass());
        assertEquals(userData.getId(), verifyUserDataBeforeSave.getId());
    }

    @Test
    void shouldUpdateUserDataWithSuccess() {
        var knownId = userData.getId();

        when(userDataRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(userData));
        when(verifyUserNameMock.verifyUserDataBeforeUpdate(userData))
                .thenReturn(false);
        when(userDataRepositoryMock.save(userData)).thenReturn(userData);

        userDataCrudService.update(userData, knownId);

        assertEquals(knownId, knownIdCapture.getValue());
        assertEquals(userData.getUserName(), userDataRepositoryMock.save(userData).getUserName());
        assertEquals(userData.getClass(), userDataRepositoryMock.save(userData).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.USER_DATA_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(userDataRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(userData));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    userDataCrudService.update(
                            userData, userData.getId());
                }
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                userDataCrudService.update(userData, userData.getId()));
    }

    @Test
    void shouldDeleteWithSuccess() {
        var knownId = userData.getId();
        when(userDataRepositoryMock.findById(knownId))
                .thenReturn(Optional.of(userData));
        doNothing().when(userDataRepositoryMock).delete(userData);

        userDataCrudService.delete(knownId);

        verify(userDataRepositoryMock,
                times(1)).delete(userData);

    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        String messageError = getString(MessagesKeyType.USER_DATA_NOT_FOUND.message);
        var unknownId = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
        when(userDataRepositoryMock.findById(UUID.fromString(unknownId)))
                .thenReturn(Optional.of(userData));
        doNothing().when(userDataRepositoryMock).delete(userData);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    userDataCrudService.delete(userData.getId());
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                userDataCrudService.delete(userData.getId()));
        verify(userDataRepositoryMock,
                times(0)).delete(userData);
    }
}