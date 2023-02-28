package br.com.amorim.supermarket.service.userdata.userdetail.encryptpassword;

import br.com.amorim.supermarket.model.userdata.UserData;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class EncryptedPasswordImpl implements EncryptedPassword {

    private PasswordEncoder passwordEncoder;

    @Override
    public void encrypt(UserData userData) {
        var encryptedPassword = passwordEncoder.encode(userData.getPassword());
        userData.setPassword(encryptedPassword);
    }
}
