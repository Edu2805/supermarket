package br.com.amorim.supermarket.service.userdata.userdetail.validaterole;

import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTO;
import br.com.amorim.supermarket.model.userdata.UserData;

public interface ValidateRole {

    void validateUserRole(CredentialsDTO credentialsDTO);
}
