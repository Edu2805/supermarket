package br.com.amorim.supermarket.service.providerproduct.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom.VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyMunicipalOrStateRegistrationProviderProductImpl implements
        VerifyMunicipalOrStateRegistrationProviderProduct {

    private VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustom verifyMunicipalOrStateRegistrationCustom;
    private ProviderProductRepository providerProductRepository;

    @Override
    public boolean verifyMunicipalOrStateRegistrationBeforeSave(ProviderProduct providerProduct) {
        if (verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct) == 1) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.PROVIDER_PRODUCT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message));
        }
        if (verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct) == 2) {
            throw new BusinessRuleException(
                    getString(MessagesKeyType.PROVIDER_PRODUCT_STATE_REGISTER_ALREADY_EXISTS.message));
        }
        return false;
    }

    @Override
    public boolean verifyMunicipalOrStateRegistrationBeforeUpdate(ProviderProduct providerProduct) {
        var getProducts = providerProductRepository.findAll();
        getProducts.forEach(provider -> {
            if (provider.getMunicipalRegistration() != null && (
                    provider.getMunicipalRegistration().equals(providerProduct.getMunicipalRegistration()))) {
                if (!provider.getId().equals(providerProduct.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.PROVIDER_PRODUCT_MUNICIPAL_REGISTRATION_ALREADY_EXISTS_WHEN_UPDATE.message));
                }
            }
            if (provider.getStateRegistration() != null && (
                    provider.getStateRegistration().equals(providerProduct.getStateRegistration()))) {
                if (!provider.getId().equals(providerProduct.getId())) {
                    throw new BusinessRuleException(getString(
                            MessagesKeyType.PROVIDER_PRODUCT_STATE_REGISTRATION_ALREADY_EXISTS_WHEN_UPDATE.message));
                }
            }
        });
        return false;
    }
}
