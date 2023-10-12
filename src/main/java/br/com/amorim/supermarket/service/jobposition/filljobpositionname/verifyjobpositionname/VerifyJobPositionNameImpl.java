package br.com.amorim.supermarket.service.jobposition.filljobpositionname.verifyjobpositionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.repository.jobposition.JobPositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyJobPositionNameImpl implements VerifyJobPositionName {

    private JobPositionRepository jobPositionRepository;
    @Override
    public boolean isPositionNameAlreadyExistsInSalary(JobPosition jobPosition) {
        var findByPositionName = jobPositionRepository.findByName(jobPosition.getName());
        if (findByPositionName.isPresent()) {
            throw new BusinessRuleException(getString(
                    MessagesKeyType.JOB_POSITION_NAME_ALREADY_EXISTS.message));
        }
        return false;
    }
}
