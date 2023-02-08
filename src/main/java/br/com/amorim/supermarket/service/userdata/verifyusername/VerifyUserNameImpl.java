package br.com.amorim.supermarket.service.userdata.verifyusername;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.repository.userdata.verifyusernamerepositorycustom.VerifyUserNameRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyUserNameImpl implements VerifyUserName {

    private VerifyUserNameRepositoryCustom verifyUserNameRepositoryCustom;
    private UserDataRepository userDataRepository;

    @Override
    public boolean verifyUserDataBeforeSave(UserData userData) {
        if (verifyUserNameRepositoryCustom.isUserNameAlreadyExistsInTheDatabase(userData)) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.USER_DATA_USER_NAME_ALREADY_EXISTS_WHEN_SAVE.message));
        }
        return false;
    }

    @Override
    public boolean verifyUserDataBeforeUpdate(UserData userData) {
        var getUsers = userDataRepository.findAll();
        getUsers.forEach(user -> {
            if (user.getUserName() != null && (
                    user.getUserName().equals(userData.getUserName()))) {
                if (!user.getId().equals(userData.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.USER_DATA_USER_NAME_ALREADY_EXISTS_WHEN_UPDATE.message));
                }
            }
        });
        return false;
    }
}
