package br.com.amorim.supermarket.service.subsection.verifysubsectionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifySubSectionNameImpl implements VerifySubSectionName {

    private SubSectionRepository subSectionRepository;

    @Override
    public boolean existsBySubSectionNameBeforeSave(SubSection subSection) {
        if (subSectionRepository.existsByName(subSection.getName())) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.SUB_SECTION_NAME_ALREADY_EXISTS.message));
        }
        return false;
    }

    @Override
    public boolean existsBySubSectionNameBeforeUpdate(SubSection subSection) {
        subSectionRepository.findAll()
                .forEach(existingSubSection -> {
                    if(existingSubSection.getName().equals(subSection.getName())) {
                        if (!existingSubSection.getId().equals(subSection.getId())) {
                            throw new BusinessRuleException(
                                    getString(MessagesKeyType.SUB_SECTION_NAME_ALREADY_EXISTS.message));
                        }
                    }
                });
        return false;
    }
}
