package br.com.amorim.supermarket.service.userdata.verifyusername;

import br.com.amorim.supermarket.model.userdata.UserData;

public interface VerifyUserName {

    boolean verifyUserDataBeforeSave(UserData userData);
    boolean verifyUserDataBeforeUpdate(UserData userData);
}
