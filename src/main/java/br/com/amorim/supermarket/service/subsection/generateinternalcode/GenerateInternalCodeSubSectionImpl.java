package br.com.amorim.supermarket.service.subsection.generateinternalcode;

import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.generateinternalcoderepositorycustom.GenerateInternalCodeSubSectionRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateInternalCodeSubSectionImpl implements GenerateInternalCodeSubSection {

    private GenerateInternalCodeSubSectionRepositoryCustom generateInternalCodeSubSectionRepositoryCustom;

    @Override
    public BigInteger generate(SubSection subSection) {
        return generateInternalCodeSubSectionRepositoryCustom
                .generateInternalCode(subSection);
    }
}
