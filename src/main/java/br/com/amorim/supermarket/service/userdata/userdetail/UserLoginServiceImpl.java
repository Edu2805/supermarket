package br.com.amorim.supermarket.service.userdata.userdetail;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidpasswordexception.InvalidPasswordException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.configuration.security.roles.RoleType;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTO;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTORoleString;
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
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.ADMIN;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.BUYER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.DEPARTMENT_MANAGER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.EMPLOYEE;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.FINANCE;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.HEAD;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.HR;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.MANAGER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.RECEIPT;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.SECTION_MANAGER;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private ValidateRole validateRole;

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
            credentialsDTO.setRole(mapperRoleType(credentialsDTORoleString.getRole()));
        }
        return credentialsDTO;
    }

    private RoleType mapperRoleType(String role) {
        RoleType roleType = null;
        if (getString(ADMIN.name()).equals(role)) {
            roleType = ADMIN;
        } else if (getString(EMPLOYEE.name()).equals(role)) {
            roleType = EMPLOYEE;
        }
        else if (getString(SECTION_MANAGER.name()).equals(role)) {
            roleType = SECTION_MANAGER;
        }
        else if (getString(DEPARTMENT_MANAGER.name()).equals(role)) {
            roleType = DEPARTMENT_MANAGER;
        }
        else if (getString(MANAGER.name()).equals(role)) {
            roleType = MANAGER;
        }
        else if (getString(BUYER.name()).equals(role)) {
            roleType = BUYER;
        }
        else if (getString(HEAD.name()).equals(role)) {
            roleType = HEAD;
        }
        else if (getString(HR.name()).equals(role)) {
            roleType = HR;
        }
        else if (getString(FINANCE.name()).equals(role)) {
            roleType = FINANCE;
        }
        else if (getString(RECEIPT.name()).equals(role)) {
            roleType = RECEIPT;
        }
        return roleType;
    }
}
