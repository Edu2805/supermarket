package br.com.amorim.supermarket.service.userdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.service.userdata.setisemployee.UserDataUpdateIsEmployee;
import br.com.amorim.supermarket.service.userdata.setusernameinperson.UserNameInPerson;
import br.com.amorim.supermarket.service.userdata.userdetail.encryptpassword.EncryptedPassword;
import br.com.amorim.supermarket.service.userdata.verifyusername.VerifyUserName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.apache.logging.log4j.ThreadContext.isEmpty;

@AllArgsConstructor

@Service
public class UserDataCrudServiceImpl implements UserDataCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private UserDataRepository userDataRepository;
    private PersonRepository personRepository;
    private VerifyPageSize verifyPageSize;
    private VerifyUserName verifyUserName;
    private UserNameInPerson userNameInPerson;
    private UserDataUpdateIsEmployee userDataUpdateIsEmployee;
    private EncryptedPassword encryptedPassword;

    @Override
    public Page<UserData> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return userDataRepository.findAll(pageableRequest);
    }

    @Override
    public UserData findById (UUID id) {
        return userDataRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.USER_DATA_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public UserData save (UserData userData) {
        verifyUserName.verifyUserDataBeforeSave(userData);
        setFields(userData);
        return userDataRepository.save(userData);
    }

    private void setFields(UserData userData) {
        encryptedPassword.encrypt(userData);
        userData.setIsEmployee(false);
        userData.setRegistrationDate(Timestamp.from(Instant.now()));
    }

    @Transactional
    @Override
    public void update (UserData userData, UUID id) {
        userDataRepository.findById(id)
                .map(existingUserData -> {
                    userData.setId(existingUserData.getId());
                    verifyUserName.verifyUserDataBeforeUpdate(userData);
                    updateFieldsUserData(userData);
                    userDataRepository.save(userData);
                    return existingUserData;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.USER_DATA_NOT_FOUND.message)));
    }

    void updateFieldsUserData(UserData userData) {
        userNameInPerson.setUserNameInPerson(userData);
        userDataUpdateIsEmployee.isUserEmployee(userData);
        userData.setRegistrationDate(Timestamp.from(Instant.now()));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        userDataRepository.findById(id)
                .map(existingUserData -> {
                    userDataRepository.delete(existingUserData);
                    return existingUserData;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.USER_DATA_NOT_FOUND.message)));
    }

    @Override
    public UserData findByUserName(UserData userData) {
        return userDataRepository.findByUserName(userData.getUserName())
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.USER_DATA_NOT_FOUND.message));
        });
    }

    @Override
    public List<UserData> findAllUsersIsEmployee() {
        List<UserData> usersAvailable = new ArrayList<>();
        var usersAreNotEmployee = userDataRepository.findByIsEmployee(false);
        usersAreNotEmployee.forEach(userData -> {
            var newUsers = personRepository.findByUserData(userData);
            if(newUsers.isEmpty()) {
                usersAvailable.add(userData);
            }
        });
        return usersAvailable;
    }
}
