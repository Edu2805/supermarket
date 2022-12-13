package br.com.amorim.supermarket.repository.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDataReposotiry extends JpaRepository<ProductData, UUID> {
}
