package br.com.amorim.supermarket.service.employee.employeefullname;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class EmployeeFullNameImpl implements EmployeeFullName {

    private PersonRepository personRepository;

    @Override
    public String fillEmployeeFullName(Employee employee) {
        var firstName = "";
        var middleName = "";
        var lastName = "";
        var person = personRepository.findById(employee.getPerson().getId());

        if (person.isPresent()) {
            firstName = person.get().getFirstName();
            lastName = person.get().getLastName();
            if (!person.get().getMiddleName().isEmpty()) {
                middleName = person.get().getMiddleName();
                return firstName + " " + middleName + " " + lastName;
            }
        }
        return firstName + " " + lastName;
    }
}
