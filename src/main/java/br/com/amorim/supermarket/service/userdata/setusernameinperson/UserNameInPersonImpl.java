package br.com.amorim.supermarket.service.userdata.setusernameinperson;

import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class UserNameInPersonImpl implements UserNameInPerson {

    private PersonRepository personRepository;

    @Override
    public void setUserNameInPerson(UserData userData) {
        var findUserDataInPerson = personRepository.findByUserData(userData);
        if(findUserDataInPerson.isPresent() && userData.getUserName() != null) {
            var personUserName = findUserDataInPerson.get().getUserData().getUserName();
            if (!userData.getUserName().equals(personUserName)) {
                personRepository.findById(findUserDataInPerson.get().getId())
                    .map(person -> {
                        person.setEmail(userData.getUserName());
                        personRepository.save(person);
                        return person;
                    });
            }
        }
    }
}
