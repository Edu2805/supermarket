package br.com.amorim.supermarket.service.userdata;

import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.UserStatusDTO;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.service.common.genericcrudservice.CrudServiceCommon;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserDataCrudService extends CrudServiceCommon<UserData, UUID> {

    UserData findByUserName (UserData userData);
    List<UserData> findAllUsersIsEmployee();
    Page<UserData> getAllNotApproved(int page, int size);
    void changeApproveStatus(UUID id, UserStatusDTO userStatusDTO);
}
