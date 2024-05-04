package br.com.amorim.supermarket.repository.paymentoptions;

import br.com.amorim.supermarket.model.paymentoptions.PaymentOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentOptionsRepository extends JpaRepository<PaymentOptions, UUID> {
}
