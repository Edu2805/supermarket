package br.com.amorim.supermarket.service.productdata.validatesubsection;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ValidateProductSubSectionImpl implements ValidateProductSubSection {

    private SubSectionRepository subSectionRepository;

    @Override
    public boolean validate(ProductData productData) {
        Optional<SubSection>  subSection = subSectionRepository.findById(productData.getSubSection().getId());
        if (subSection.isEmpty()) {
            throw new NotFoundException(getString(MessagesKeyType.PRODUCT_DATA_SUB_SECTION_NON_EXISTENT.message));
        }
        return false;
    }
}
