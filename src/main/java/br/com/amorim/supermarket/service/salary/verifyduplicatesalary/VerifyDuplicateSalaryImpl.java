package br.com.amorim.supermarket.service.salary.verifyduplicatesalary;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyDuplicateSalaryImpl implements VerifyDuplicateSalary {

    private SalaryRepository salaryRepository;

    @Override
    public boolean isDuplicateSalaryBeforeSave(Salary salary) {
        var findPositionAndSalaryRange = salaryRepository
                .findByPositionAndSalaryRange(salary.getPosition(), salary.getSalaryRange());
        if (findPositionAndSalaryRange.isPresent()) {
            throw new BusinessRuleException(getString(MessagesKeyType.SALARY_IS_DUPLICATED.message));
        }
        return false;
    }

    @Override
    public boolean isDuplicateSalaryBeforeUpdate(Salary salary) {
        salaryRepository.findAll()
                .forEach(existingSalary -> {
                    if((existingSalary.getPosition().equals(salary.getPosition()) &&
                            existingSalary.getSalaryRange().equals(salary.getSalaryRange()))) {
                        if (!existingSalary.getId().equals(salary.getId())) {
                            throw new BusinessRuleException(getString(MessagesKeyType.SALARY_IS_DUPLICATED.message));
                        }
                    }
                });

        return false;
    }
}
