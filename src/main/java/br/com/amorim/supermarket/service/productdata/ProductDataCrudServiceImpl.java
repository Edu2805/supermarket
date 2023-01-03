package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;

import java.util.List;
import java.util.UUID;

/**
 * Contratos para a implementação do CRUD
 */

public interface ProductDataCrudServiceImpl {

    List<ProductData> getAll();
    ProductData findById(UUID id);
    ProductData save (ProductData productData);
    void update (ProductData productData, UUID id);
    void delete (UUID id);
}
