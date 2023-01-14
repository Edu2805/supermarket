package br.com.amorim.supermarket.service.providerproduct.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom.VerifyMunicipalOrStateRegistrationCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class VerifyMunicipalOrStateRegistrationImpl implements VerifyMunicipalOrStateRegistration {

    private VerifyMunicipalOrStateRegistrationCustom verifyMunicipalOrStateRegistrationCustom;

    @Override
    public boolean verifyMunicipalRegistration(ProviderProduct providerProduct) {
        return verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct);
    }
}
