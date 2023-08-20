package br.com.amorim.supermarket.repository.scholarity;

import br.com.amorim.supermarket.model.scholarity.Scholarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScholarityRepository extends JpaRepository<Scholarity, UUID> {
}
