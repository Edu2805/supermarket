package br.com.amorim.supermarket.repository.otheraddition;

import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OtherAdditionRepository extends JpaRepository<OtherAddition, UUID> {
}
