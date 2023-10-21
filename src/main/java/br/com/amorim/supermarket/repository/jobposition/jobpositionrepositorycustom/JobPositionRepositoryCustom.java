package br.com.amorim.supermarket.repository.jobposition.jobpositionrepositorycustom;

import br.com.amorim.supermarket.model.jobposition.JobPosition;

import java.math.BigInteger;
import java.util.List;

public interface JobPositionRepositoryCustom {

    BigInteger generateInternalCode(JobPosition jobPosition);
    List<JobPosition> existsJobPositionInEmployee();
}
