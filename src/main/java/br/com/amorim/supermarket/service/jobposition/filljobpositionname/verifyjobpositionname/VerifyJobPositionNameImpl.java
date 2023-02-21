package br.com.amorim.supermarket.service.jobposition.filljobpositionname.verifyjobpositionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyJobPositionNameImpl implements VerifyJobPositionName {

    private SalaryRepository salaryRepository;
    @Override
    public boolean isNamePositionNameAlreadyExistsInSalary(JobPosition jobPosition) {
        var findByPositionName = salaryRepository.findByPosition(jobPosition.getName());
        if (findByPositionName.isPresent()) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.JOB_POSITION_NAME_ALREADY_EXISTS.message));
        }
        return false;
    }
}
