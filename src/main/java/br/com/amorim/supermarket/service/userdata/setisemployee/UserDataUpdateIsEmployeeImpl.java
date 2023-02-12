package br.com.amorim.supermarket.service.userdata.setisemployee;

import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class UserDataUpdateIsEmployeeImpl implements UserDataUpdateIsEmployee {

    private UserDataRepository userDataRepository;

    @Override
    public void isUserEmployee(UserData userData) {
       userDataRepository.findById(userData.getId())
               .map(user -> {
                   userData.setIsEmployee(user.getIsEmployee());
                   return userData;
               });
    }
}
