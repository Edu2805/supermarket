package br.com.amorim.supermarket.service.userdata.userdetail.validaterole;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidpasswordexception.InvalidPasswordException;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTO;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ValidateRoleImpl implements ValidateRole {

    private UserDataRepository userDataRepository;

    @Override
    public void validateUserRole(CredentialsDTO credentialsDTO) {
        userDataRepository.findByUserName(credentialsDTO.getLogin())
                .ifPresent(existentUser -> {
                    if (existentUser.getRole() != credentialsDTO.getRole()) {
                        throw new InvalidPasswordException(
                                getString(MessagesKeyType.INVALID_USER_ROLE.message));
                    }});
    }
}
