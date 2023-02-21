package br.com.amorim.supermarket.service.userdata;

import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.service.common.genericcrudservice.CrudServiceCommon;

import java.util.UUID;

public interface UserDataCrudService extends CrudServiceCommon<UserData, UUID> {
}
