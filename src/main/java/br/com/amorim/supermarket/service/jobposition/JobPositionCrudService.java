package br.com.amorim.supermarket.service.jobposition;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.service.common.genericcrudservice.CrudServiceCommon;

import java.util.List;
import java.util.UUID;

public interface JobPositionCrudService extends CrudServiceCommon<JobPosition, UUID> {

    List<JobPosition> isThereAJobPositionAlreadyRegisteredForTheEmployee();
}
