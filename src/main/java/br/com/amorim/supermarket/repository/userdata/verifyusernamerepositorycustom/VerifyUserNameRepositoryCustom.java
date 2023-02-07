package br.com.amorim.supermarket.repository.userdata.verifyusernamerepositorycustom;

import br.com.amorim.supermarket.model.userdata.UserData;

public interface VerifyUserNameRepositoryCustom {

    boolean isUserNameAlreadyExistsInTheDatabase(UserData userData);
}
