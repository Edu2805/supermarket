package br.com.amorim.supermarket.service.department.verifymainsectionbeforedelete;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyMainSectionBeforeDeleteImpl implements VerifyMainSectionBeforeDelete {

    private MainSectionRepository mainSectionRepository;

    @Override
    public void verifyDepartmentMainSection(Department department) {
        var existsByMainSection = mainSectionRepository.existsByDepartment(department);
        if (existsByMainSection) {
            throw new NotFoundException(getString(
                    MessagesKeyType.ALREADY_EXISTS_MAIN_SECTION_FOR_THE_DEPARTMENT.message));
        }
    }
}
