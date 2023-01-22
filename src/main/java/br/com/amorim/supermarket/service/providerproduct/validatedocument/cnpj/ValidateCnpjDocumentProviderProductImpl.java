package br.com.amorim.supermarket.service.providerproduct.validatedocument.cnpj;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invaliddocument.InvalidDocumentException;
import br.com.amorim.supermarket.common.verifydocument.cnpj.VerifyCNPJ;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ValidateCnpjDocumentProviderProductImpl implements ValidateCnpjDocumentProviderProduct {

    private VerifyCNPJ verifyCNPJ;

    @Override
    public boolean isCnpj(ProviderProduct providerProduct) {
        if (!verifyCNPJ.isCNPJ(providerProduct.getSubscriptionNumber())) {
            throw new InvalidDocumentException(
                    getString(MessagesKeyType.PROVIDER_PRODUCT_INCORRECT_CNPJ_NUMBER.message));
        }
        return false;
    }
}
