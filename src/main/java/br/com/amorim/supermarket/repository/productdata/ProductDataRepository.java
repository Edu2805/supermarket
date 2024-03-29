package br.com.amorim.supermarket.repository.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.subsection.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, UUID> {

    boolean existsBySubSection(SubSection subSection);
    boolean existsByProviderProduct(ProviderProduct providerProduct);
}
