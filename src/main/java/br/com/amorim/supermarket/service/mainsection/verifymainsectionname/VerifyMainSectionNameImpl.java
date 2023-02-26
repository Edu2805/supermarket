package br.com.amorim.supermarket.service.mainsection.verifymainsectionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyMainSectionNameImpl implements VerifyMainSectionName {

    private MainSectionRepository mainSectionRepository;

    @Override
    public boolean existsByMainSectionNameBeforeSave(MainSection mainSection) {
        if (mainSectionRepository.existsByName(mainSection.getName())) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.MAIN_SECTION_NAME_ALREADY_EXISTS.message));
        }
        return false;
    }

    @Override
    public boolean existsByMainSectionNameBeforeUpdate(MainSection mainSection) {
        mainSectionRepository.findAll()
                .forEach(existingMainSection -> {
                    if(existingMainSection.getName().equals(mainSection.getName())) {
                        if (!existingMainSection.getId().equals(mainSection.getId())) {
                            throw new BusinessRuleException(
                                    getString(MessagesKeyType.MAIN_SECTION_NAME_ALREADY_EXISTS.message));
                        }
                    }
                });
        return false;
    }
}
