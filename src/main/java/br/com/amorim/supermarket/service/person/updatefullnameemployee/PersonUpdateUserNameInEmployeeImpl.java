package br.com.amorim.supermarket.service.person.updatefullnameemployee;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class PersonUpdateUserNameInEmployeeImpl implements PersonUpdateUserNameInEmployee {

    private EmployeeRepository employeeRepository;

    @Override
    public void updateFullNameEmployee(Person person) {
        String isDifferentFullName;
        var findPersonInEmployee = employeeRepository.findByPerson(person);
        if(findPersonInEmployee.isPresent()
                && person.getFirstName() != null
                && person.getLastName() != null) {

            var personFullName = findPersonInEmployee.get().getFullName();
            if (person.getMiddleName() == null || person.getMiddleName().isEmpty()) {
                person.setMiddleName("");
                isDifferentFullName = person.getFirstName() + " " + person.getLastName();
                if (!isDifferentFullName.equals(personFullName)) {
                    employeeRepository.findById(findPersonInEmployee.get().getId())
                            .map(employee -> {
                                employee.setFullName(isDifferentFullName);
                                employeeRepository.save(employee);
                                return employee;
                            });
                }
            } else {
                isDifferentFullName = person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName();
                if (!isDifferentFullName.equals(personFullName)) {
                    employeeRepository.findById(findPersonInEmployee.get().getId())
                            .map(employee -> {
                                employee.setFullName(isDifferentFullName);
                                employeeRepository.save(employee);
                                return employee;
                            });
                }
            }
        }
    }
}
