package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProductDataCrudService {

    Page<ProductData> getAll();
    ProductData findById(UUID id);
    ProductData save (ProductData productData);
    void update (ProductData productData, UUID id);
    void delete (UUID id);
}
