package br.com.amorim.supermarket.repository.unity;

import br.com.amorim.supermarket.model.unity.Unity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnityRepository extends JpaRepository<Unity, UUID> {
}
