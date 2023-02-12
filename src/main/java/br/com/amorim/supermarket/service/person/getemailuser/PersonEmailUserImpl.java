package br.com.amorim.supermarket.service.person.getemailuser;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class PersonEmailUserImpl implements PersonEmailUser {

    private UserDataRepository userDataRepository;

    @Override
    public void fillEmailPerson(Person person) {
        var findUser = userDataRepository.findById(person.getUserData().getId());
        findUser.ifPresent(userData -> person.setEmail(userData.getUserName()));
    }
}
