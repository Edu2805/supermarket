package br.com.amorim.supermarket.service.person.verifypersonemployee;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyPersonEmployeeImpl implements VerifyPersonEmployee {

    private EmployeeRepository employeeRepository;
    @Override
    public void verifyPersonEmployeeBeforeDelete(Person person) {
        if(employeeRepository.findByPerson(person).isPresent()) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.PERSON_EMPLOYEE_DELETE_EXISTS.message));
        }
    }
}
