package br.com.amorim.supermarket.service.providerproduct;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProviderProductCrudService {

    Page<ProviderProduct> getAll(int page, int size);
    ProviderProduct findById(UUID id);
    ProviderProduct save (ProviderProduct providerProduct);
    void update (ProviderProduct providerProduct, UUID id);
    void delete (UUID id);
}
