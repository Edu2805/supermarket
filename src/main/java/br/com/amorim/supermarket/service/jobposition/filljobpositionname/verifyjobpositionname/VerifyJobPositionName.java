package br.com.amorim.supermarket.service.jobposition.filljobpositionname.verifyjobpositionname;

import br.com.amorim.supermarket.model.jobposition.JobPosition;

public interface VerifyJobPositionName {

    boolean isNamePositionNameAlreadyExistsInSalary(JobPosition jobPosition);
}
