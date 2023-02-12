package br.com.amorim.supermarket.service.employee.admitemployee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class AdmitEmployeeImpl implements AdmitEmployee {

    private UserDataRepository userDataRepository;
    private PersonRepository personRepository;

    @Override
    public void isEmployee(Employee employee) {
        var findPerson = personRepository
                .findById(employee.getPerson().getId());

        if (findPerson.isPresent()) {
            var findUser = userDataRepository
                    .findById(findPerson.get().getUserData().getId());
            if (findUser.isPresent()) {
                findUser.get().setIsEmployee(true);
                userDataRepository.save(findUser.get());
            }
        }
    }
}
