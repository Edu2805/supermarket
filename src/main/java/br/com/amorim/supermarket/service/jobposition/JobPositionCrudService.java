package br.com.amorim.supermarket.service.jobposition;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface JobPositionCrudService {

    Page<JobPosition> getAll(int page, int size);
    JobPosition findById(UUID id);
    JobPosition save (JobPosition jobPosition);
    void update (JobPosition jobPosition, UUID id);
    void delete (UUID id);
}
