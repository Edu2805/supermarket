package br.com.amorim.supermarket.service.userdata.userdetail.encryptpassword;

import br.com.amorim.supermarket.model.userdata.UserData;

public interface EncryptedPassword {

    void encrypt(UserData userData);
}
