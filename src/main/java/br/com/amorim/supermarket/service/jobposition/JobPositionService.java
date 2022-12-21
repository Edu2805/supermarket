package br.com.amorim.supermarket.service.jobposition;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.repository.jobposition.JobPositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class JobPositionService {

    JobPositionRepository jobPositionRepository;

    public List<JobPosition> getAll () {
        return jobPositionRepository.findAll();
    }

    public JobPosition findById (UUID id) {
        return jobPositionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Cargo não encontrado");
                });
    }

    @Transactional
    public JobPosition save (JobPosition jobPosition) {
        return jobPositionRepository.save(jobPosition);
    }

    @Transactional
    public void update (JobPosition jobPosition, UUID id) {
        jobPositionRepository.findById(id)
                .map(existingJobPosition -> {
                    jobPosition.setId(existingJobPosition.getId());
                    jobPositionRepository.save(jobPosition);
                    return existingJobPosition;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Cargo não encontrado"));
    }

    @Transactional
    public void delete (UUID id) {
        jobPositionRepository.findById(id)
                .map(existingJobPosition -> {
                    jobPositionRepository.delete(existingJobPosition);
                    return existingJobPosition;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Cargo não encontrado"));
    }
}
