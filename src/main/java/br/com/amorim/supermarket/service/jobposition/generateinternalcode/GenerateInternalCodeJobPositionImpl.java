package br.com.amorim.supermarket.service.jobposition.generateinternalcode;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.repository.jobposition.jobpositionrepositorycustom.JobPositionRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@AllArgsConstructor

@Component
public class GenerateInternalCodeJobPositionImpl implements GenerateInternalCodeJobPosition {

    private JobPositionRepositoryCustom generateInternalCodeJobPositionRepositoryCustom;

    @Override
    public BigInteger generate(JobPosition jobPosition) {
        return generateInternalCodeJobPositionRepositoryCustom
                .generateInternalCode(jobPosition);
    }
}
