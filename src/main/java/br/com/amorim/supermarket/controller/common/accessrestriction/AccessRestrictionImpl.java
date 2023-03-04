package br.com.amorim.supermarket.controller.common.accessrestriction;

import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor

@Component
public class AccessRestrictionImpl implements AccessRestriction {

    private UserDataRepository userDataRepository;

    @Override
    public boolean isAuthorized(UUID id) {
        AtomicBoolean isUser = new AtomicBoolean(false);
        userDataRepository.findAll().forEach(userData -> {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                var password = ((UserDetails)principal).getPassword();
                if (userData.getPassword().equals(password)
                        && userData.getId().equals(id)) {
                    isUser.set(true);
                }
            }
        });
        return isUser.get();
    }
}
