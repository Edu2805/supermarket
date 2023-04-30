package br.com.amorim.supermarket.service.userdata.userdetail;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidpasswordexception.InvalidPasswordException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.amorim.supermarket.common.enums.MessagesKeyType.USER_DATA_NOT_FOUND;
import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDataRepository userDataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userData = userDataRepository.findByUserName(username)
                .orElseThrow(() ->
                        new NotFoundException(getString(MessagesKeyType.USER_DATA_NOT_FOUND.message)));
        return User
                .builder()
                .username(userData.getUserName())
                .password(userData.getPassword())
                .roles(userData.getRole().role)
                .build();
    }

    public UserDetails authenticate(UserData userData) {
        UserDetails user = loadUserByUsername(userData.getUserName());
        boolean isPasswordsAreEquals = passwordEncoder.matches(userData.getPassword(), user.getPassword());
        if (isPasswordsAreEquals) {
            return user;
        }
        throw new InvalidPasswordException(
                getString(MessagesKeyType.AUTH_INVALID_PASSWORD.message));
    }

    public void existsUserName(String userName) {
        if(!userDataRepository.findByUserName(userName).isPresent()) {
            throw new InvalidPasswordException(
                    getString(MessagesKeyType.USER_DATA_NOT_FOUND.message));
        }
    }
}
