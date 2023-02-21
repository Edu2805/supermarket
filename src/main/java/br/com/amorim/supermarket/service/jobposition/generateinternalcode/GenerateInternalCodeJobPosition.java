package br.com.amorim.supermarket.service.jobposition.generateinternalcode;

import br.com.amorim.supermarket.model.jobposition.JobPosition;

import java.math.BigInteger;

public interface GenerateInternalCodeJobPosition {

    BigInteger generate (JobPosition jobPosition);
}
