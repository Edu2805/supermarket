package br.com.amorim.supermarket.repository.establishment;

import br.com.amorim.supermarket.model.establishment.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstablisumentRepository extends JpaRepository<Establishment, UUID> {
}
