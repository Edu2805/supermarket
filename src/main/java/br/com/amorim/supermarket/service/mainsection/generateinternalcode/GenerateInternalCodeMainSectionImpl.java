package br.com.amorim.supermarket.service.mainsection.generateinternalcode;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.mainsection.generateinternalcoderepositorycustom.GenerateInternalCodeMainSectionRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateInternalCodeMainSectionImpl implements GenerateInternalCodeMainSection {

    private GenerateInternalCodeMainSectionRepositoryCustom generateInternalCodeMainSectionRepositoryCustom;
    @Override
    public BigInteger generate(MainSection mainSection) {
        return generateInternalCodeMainSectionRepositoryCustom
                .generateInternalCode(mainSection);
    }
}
