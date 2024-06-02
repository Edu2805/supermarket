package br.com.amorim.supermarket.repository.userdata.userdatarepositorycustom;

import br.com.amorim.supermarket.model.userdata.UserData;
import org.springframework.data.domain.Page;

public interface UserDataRepositoryCustom {

    boolean isUserNameAlreadyExistsInTheDatabase(UserData userData);
    Page<UserData> findByIdAndIsApproved(int page, int size);
}
