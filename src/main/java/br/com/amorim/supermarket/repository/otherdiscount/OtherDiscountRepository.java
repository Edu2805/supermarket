package br.com.amorim.supermarket.repository.otherdiscount;

import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OtherDiscountRepository extends JpaRepository<OtherDiscount, UUID> {
}
