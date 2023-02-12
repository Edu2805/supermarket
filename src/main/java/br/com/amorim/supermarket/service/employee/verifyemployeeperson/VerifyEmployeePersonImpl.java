package br.com.amorim.supermarket.service.employee.verifyemployeeperson;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.repository.employee.verifyperson.VerifyPersonRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyEmployeePersonImpl implements VerifyEmployeePerson {

    private VerifyPersonRepositoryCustom verifyPersonRepositoryCustom;

    @Override
    public boolean verifyEmployeePerson(Employee employee) {
        if (verifyPersonRepositoryCustom.existsByEmployeePerson(employee)) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.EMPLOYEE_PERSON_ALREADY_EXISTS_WHEN_SAVE.message));
        }
        return false;
    }
}
