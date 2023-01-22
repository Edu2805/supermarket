package br.com.amorim.supermarket.service.providerproduct.validatedocument.cpf;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invaliddocument.InvalidDocumentException;
import br.com.amorim.supermarket.common.verifydocument.cpf.VerifyCPF;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ValidateCpfDocumentProviderProductImpl implements ValidateCpfDocumentProviderProduct {

    private VerifyCPF verifyCPF;

    @Override
    public boolean isCpf(ProviderProduct providerProduct) {
        if (!verifyCPF.isCPF(providerProduct.getSubscriptionNumber())) {
            throw new InvalidDocumentException(
                    getString(MessagesKeyType.PROVIDER_PRODUCT_INCORRECT_CPF_NUMBER.message)
            );
        }
        return false;
    }
}
