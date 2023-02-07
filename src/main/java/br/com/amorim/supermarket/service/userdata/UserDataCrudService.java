package br.com.amorim.supermarket.service.userdata;

import br.com.amorim.supermarket.model.userdata.UserData;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserDataCrudService {

    Page<UserData> getAll(int page, int size);
    UserData findById(UUID id);
    UserData save (UserData userData);
    void update (UserData userData, UUID id);
    void delete (UUID id);
}
