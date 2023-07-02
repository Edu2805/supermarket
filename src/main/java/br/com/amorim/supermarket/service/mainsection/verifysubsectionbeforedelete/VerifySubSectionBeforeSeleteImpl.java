package br.com.amorim.supermarket.service.mainsection.verifysubsectionbeforedelete;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifySubSectionBeforeSeleteImpl implements VerifySubSectionBeforeSelete {

    private SubSectionRepository subSectionRepository;
    @Override
    public void verifyMainSectionSubSection(MainSection mainSection) {
        var existsByMainSection = subSectionRepository.existsByMainSection(mainSection);
        if (existsByMainSection) {
            throw new NotFoundException(getString(
                    MessagesKeyType.ALREADY_EXISTS_SUB_SECTION_FOR_THE_MAIN_SECTION.message));
        }
    }
}
