package br.com.amorim.supermarket.repository.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, UUID> {
    //TODO Isolar as queries em um repository custom
    @Query(value = "SELECT CASE WHEN count(p) > 0 " +
            "THEN true ELSE false END FROM ProductData AS p " +
            "where p.ean13  = ?1")
    boolean existsByEan13(String ean13);

    @Query(value = "SELECT CASE WHEN count(p) > 0 " +
            "THEN true ELSE false END FROM ProductData AS p " +
            "where p.dun14  = ?1")
    boolean existsByDun14(String dun14);
}
