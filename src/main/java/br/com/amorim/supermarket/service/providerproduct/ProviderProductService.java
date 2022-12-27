package br.com.amorim.supermarket.service.providerproduct;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class ProviderProductService {

    ProviderProductRepository providerProductRepository;

    public List<ProviderProduct> getAll() {
        return providerProductRepository.findAll();
    }

    public ProviderProduct findById(UUID id) {
        return providerProductRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Fornecedor não encontrado");
                });
    }

    @Transactional
    public ProviderProduct save (ProviderProduct providerProduct) {
        return providerProductRepository.save(providerProduct);
    }

    @Transactional
    public void update (ProviderProduct providerProduct, UUID id) {
        providerProductRepository.findById(id)
                .map(existingProvider -> {
                    providerProduct.setId(existingProvider.getId());
                    providerProductRepository.save(providerProduct);
                    return existingProvider;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Fornecedor não encontrado"));

    }

    @Transactional
    public void delete (UUID id) {
        providerProductRepository.findById(id)
                .map(provider -> {
                    providerProductRepository.delete(provider);
                    return provider;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Fornecedor não encontrado"));
    }
}
