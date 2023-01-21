package br.com.amorim.supermarket.repository.providerproduct;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProviderProductRepository extends JpaRepository<ProviderProduct, UUID> {

    Optional<ProviderProduct> findByStateRegistration(String stateRegistration);
}
