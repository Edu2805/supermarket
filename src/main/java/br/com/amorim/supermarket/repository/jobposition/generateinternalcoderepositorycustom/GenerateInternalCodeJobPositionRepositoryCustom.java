package br.com.amorim.supermarket.repository.jobposition.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.jobposition.JobPosition;

import java.math.BigInteger;

public interface GenerateInternalCodeJobPositionRepositoryCustom {

    BigInteger generateInternalCode(JobPosition jobPosition);
}
