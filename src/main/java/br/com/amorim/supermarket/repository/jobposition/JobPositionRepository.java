package br.com.amorim.supermarket.repository.jobposition;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, UUID> {
}
