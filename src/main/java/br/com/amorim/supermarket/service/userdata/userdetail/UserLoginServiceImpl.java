package br.com.amorim.supermarket.service.userdata.userdetail;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidpasswordexception.InvalidPasswordException;
import br.com.amorim.supermarket.common.exception.notapprovedexception.NotApporvedException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.controller.userdata.dto.mapper.RoleTypeMapper;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTO;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTORoleString;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.UserDataDTO;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.service.userdata.userdetail.validaterole.ValidateRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private ValidateRole validateRole;
    @Autowired
    private RoleTypeMapper roleTypeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userData = userDataRepository.findByUserName(username)
                .orElseThrow(() ->
                        new NotFoundException(getString(MessagesKeyType.USER_DATA_NOT_FOUND.message)));
        if (!userData.getIsApproved()) {
            throw new NotApporvedException(getString(MessagesKeyType.USER_DATA_NOT_APPROVED.message));
        }
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
        if(userDataRepository.findByUserName(userName).isEmpty()) {
            throw new InvalidPasswordException(
                    getString(MessagesKeyType.USER_DATA_NOT_FOUND.message));
        }
    }

    public void validateRole(CredentialsDTO credentialsDTO) {
        validateRole.validateUserRole(credentialsDTO);
    }

    public CredentialsDTO credentialMapper(CredentialsDTORoleString credentialsDTORoleString) {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        if (credentialsDTORoleString != null) {
            credentialsDTO.setLogin(credentialsDTORoleString.getLogin());
            credentialsDTO.setPassword(credentialsDTORoleString.getPassword());
            credentialsDTO.setRole(roleTypeMapper.mapperRoleType(credentialsDTORoleString.getRole()));
        }
        return credentialsDTO;
    }

    public UserDataDTO registerMapper(CredentialsDTORoleString credentialsDTORoleString) {
        UserDataDTO userDataDTO = new UserDataDTO();
        if (credentialsDTORoleString != null) {
            userDataDTO.setUserName(credentialsDTORoleString.getLogin());
            userDataDTO.setPassword(credentialsDTORoleString.getPassword());
            userDataDTO.setRole(roleTypeMapper.mapperRoleType(credentialsDTORoleString.getRole()));
        }
        return userDataDTO;
    }
}
