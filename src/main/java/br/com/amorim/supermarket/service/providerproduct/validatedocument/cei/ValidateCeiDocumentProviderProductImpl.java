package br.com.amorim.supermarket.service.providerproduct.validatedocument.cei;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ValidateCeiDocumentProviderProductImpl implements ValidateCeiDocumentProviderProduct {

    @Override
    public boolean isCei(ProviderProduct providerProduct) {
        //TODO Implementar a lógica para validação de CEI / CNO
        return false;
    }
}
