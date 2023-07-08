package br.com.amorim.supermarket.service.mainsection;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.service.mainsection.generateinternalcode.GenerateInternalCodeMainSection;
import br.com.amorim.supermarket.service.mainsection.verifymainsectionname.VerifyMainSectionName;
import br.com.amorim.supermarket.service.mainsection.verifysubsectionbeforedelete.VerifySubSectionBeforeDelete;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class MainSectionCrudServiceImpl implements MainSectionCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private MainSectionRepository mainSectionRepository;
    private VerifyPageSize verifyPageSize;
    private GenerateInternalCodeMainSection generateInternalCodeMainSection;
    private VerifyMainSectionName verifyMainSectionName;
    private VerifySubSectionBeforeDelete verifySubSectionBeforeSelete;

    @Override
    public Page<MainSection> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return mainSectionRepository.findAll(pageableRequest);
    }

    @Override
    public MainSection findById (UUID id) {
        return mainSectionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.MAIN_SECTION_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public MainSection save (MainSection mainSection) {
        verifyMainSectionName.existsByMainSectionNameBeforeSave(mainSection);
        var internalCode = generateInternalCodeMainSection.generate(mainSection);
        mainSection.setCode(internalCode);
        return mainSectionRepository.save(mainSection);
    }

    @Transactional
    @Override
    public void update (MainSection mainSection, UUID id) {
        mainSectionRepository.findById(id)
                .map(existingMainSection -> {
                    mainSection.setId(existingMainSection.getId());
                    verifyMainSectionName.existsByMainSectionNameBeforeUpdate(mainSection);
                    mainSection.setCode(existingMainSection.getCode());
                    mainSectionRepository.save(mainSection);
                    return existingMainSection;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.MAIN_SECTION_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        mainSectionRepository.findById(id)
                .map(existingMainSection -> {
                    verifySubSectionBeforeSelete.verifyMainSectionSubSection(existingMainSection);
                    mainSectionRepository.delete(existingMainSection);
                    return existingMainSection;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.MAIN_SECTION_NOT_FOUND.message)));
    }
}
