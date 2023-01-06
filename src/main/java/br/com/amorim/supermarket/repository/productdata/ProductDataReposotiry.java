package br.com.amorim.supermarket.repository.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDataReposotiry extends JpaRepository<ProductData, UUID> {

    /**
     * Consulta na base se já existe um EAN 13
     * @param ean13 código inserido
     * @return true caso já exista um EAN 13 na base
     */
    @Query(value = "SELECT CASE WHEN count(p) > 0 " +
            "THEN true ELSE false END FROM ProductData AS p " +
            "where p.ean13  = ?1")
    boolean existsByEan13(String ean13);

    /**
     * Consulta na base se já existe um DUN 14
     * @param dun14 código inserido
     * @return true caso já exista um DUN 14 na base
     */
    @Query(value = "SELECT CASE WHEN count(p) > 0 " +
            "THEN true ELSE false END FROM ProductData AS p " +
            "where p.dun14  = ?1")
    boolean existsByDun14(String dun14);
}
