package br.com.amorim.supermarket.service.subsection;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.service.subsection.generateinternalcode.GenerateInternalCodeSubSection;
import br.com.amorim.supermarket.service.subsection.verifysubsectionname.VerifySubSectionName;
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
public class SubSectionCrudServiceImpl implements SubSectionCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private SubSectionRepository subSectionRepository;
    private VerifyPageSize verifyPageSize;
    private GenerateInternalCodeSubSection generateInternalCodeSubSection;
    private VerifySubSectionName verifySubSectionName;

    @Override
    public Page<SubSection> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return subSectionRepository.findAll(pageableRequest);
    }

    @Override
    public SubSection findById (UUID id) {
        return subSectionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.SUB_SECTION_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public SubSection save (SubSection subSection) {
        verifySubSectionName.existsBySubSectionNameBeforeSave(subSection);
        var internalCode = generateInternalCodeSubSection.generate(subSection);
        subSection.setCode(internalCode);
        return subSectionRepository.save(subSection);
    }

    @Transactional
    @Override
    public void update (SubSection subSection, UUID id) {
        subSectionRepository.findById(id)
                .map(existingSubSection -> {
                    subSection.setId(existingSubSection.getId());
                    verifySubSectionName.existsBySubSectionNameBeforeUpdate(subSection);
                    subSection.setCode(existingSubSection.getCode());
                    subSectionRepository.save(subSection);
                    return existingSubSection;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.SUB_SECTION_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        subSectionRepository.findById(id)
                .map(existingSubSection -> {
                    subSectionRepository.delete(existingSubSection);
                    return existingSubSection;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.SUB_SECTION_NOT_FOUND.message)));
    }
}
