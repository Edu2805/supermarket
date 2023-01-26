package br.com.amorim.supermarket.service.providerproduct.verifymunicipalorstateregistration;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom.VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustom;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCPF;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.providerproduct.ProviderProductTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class VerifyMunicipalOrStateRegistrationProviderProductImplTest {

    @InjectMocks
    private VerifyMunicipalOrStateRegistrationProviderProductImpl verifyMunicipalOrStateRegistrationProviderProduct;
    @Mock
    private VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustom verifyMunicipalOrStateRegistrationCustom;
    @Mock
    private ProviderProductRepository providerProductRepository;

    private GenerateCPF generateCPF;
    private GenerateCNPJ generateCNPJ;
    private ProviderProduct providerProduct1;
    private ProviderProduct providerProduct2;

    private void startProvider () {
        generateCPF = new GenerateCPF();
        generateCNPJ = new GenerateCNPJ();
        ProviderProductTest providerProductTest1 = new ProviderProductTest();
        providerProduct1 = providerProductTest1.generateProvider();

        generateCPF = new GenerateCPF();
        generateCNPJ = new GenerateCNPJ();
        ProviderProductTest providerProductTest2 = new ProviderProductTest();
        providerProduct2 = providerProductTest2.generateProvider();
    }

    @BeforeEach
    void setUp() {
        startProvider();
    }

    @Test
    void shouldReturnABusinessExceptionMessageIfMunicipalRegistrationAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PROVIDER_PRODUCT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message);

        when(verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct1))
                .thenReturn(1);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationProviderProduct
                            .verifyMunicipalOrStateRegistrationBeforeSave(providerProduct1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationProviderProduct
                        .verifyMunicipalOrStateRegistrationBeforeSave(providerProduct1));
    }

    @Test
    void shouldReturnABusinessExceptionMessageIfStateRegistrationAlreadyExistsBeforeSave() {
        String messageError = getString(MessagesKeyType
                .PROVIDER_PRODUCT_STATE_REGISTER_ALREADY_EXISTS.message);

        when(verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct1))
                .thenReturn(2);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationProviderProduct
                            .verifyMunicipalOrStateRegistrationBeforeSave(providerProduct1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationProviderProduct
                        .verifyMunicipalOrStateRegistrationBeforeSave(providerProduct1));
    }

    @Test
    void shouldReturnFalseWhenMunicipalAndStateRegistrationNotExistsBeforeSave() {
        when(verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct1))
                .thenReturn(3);
        when(verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct1))
                .thenReturn(3);

        var verifyMunicipalOrStateRegistration = verifyMunicipalOrStateRegistrationProviderProduct
                .verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct1);

        assertFalse(verifyMunicipalOrStateRegistration);
    }

    @Test
    void shouldReturnFalseWhenMunicipalRegistrationNotExistsBeforeUpdate() {
        List<ProviderProduct> providerProductList = new ArrayList<>();
        providerProductList.add(providerProduct1);
        providerProductList.add(providerProduct2);

        when(providerProductRepository.findAll()).thenReturn(providerProductList);

        var verifyMunicipalRegister = verifyMunicipalOrStateRegistrationProviderProduct
                .verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct1);

        assertFalse(verifyMunicipalRegister);
    }

    @Test
    void shouldReturnABusinessRuleExceptionMessageWhenMunicipalRegistrationIsEqualsAndIdNoLongerExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType
                .PROVIDER_PRODUCT_MUNICIPAL_REGISTRATION_ALREADY_EXISTS_WHEN_UPDATE.message);
        List<ProviderProduct> providerProductList = new ArrayList<>();
        providerProduct1.setMunicipalRegistration(providerProduct2.getMunicipalRegistration());
        providerProductList.add(providerProduct1);
        providerProductList.add(providerProduct2);

        when(providerProductRepository.findAll()).thenReturn(providerProductList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationProviderProduct
                            .verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationProviderProduct
                        .verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct1));
    }

    @Test
    void shouldReturnFalseWhenStateRegistrationNotExistsBeforeUpdate() {
        List<ProviderProduct> providerProductList = new ArrayList<>();
        providerProduct1.setMunicipalRegistration(null);
        providerProductList.add(providerProduct1);
        providerProductList.add(providerProduct2);

        when(providerProductRepository.findAll()).thenReturn(providerProductList);

        var verifyMunicipalRegister = verifyMunicipalOrStateRegistrationProviderProduct
                .verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct1);

        assertFalse(verifyMunicipalRegister);
    }

    @Test
    void shouldReturnABusinessRuleExceptionMessageWhenStateRegistrationIsEqualsAndIdNoLongerExistsBeforeUpdate() {
        String messageError = getString(MessagesKeyType
                .PROVIDER_PRODUCT_STATE_REGISTRATION_ALREADY_EXISTS_WHEN_UPDATE.message);
        List<ProviderProduct> providerProductList = new ArrayList<>();
        providerProduct1.setStateRegistration(providerProduct2.getStateRegistration());
        providerProductList.add(providerProduct1);
        providerProductList.add(providerProduct2);

        when(providerProductRepository.findAll()).thenReturn(providerProductList);

        String exceptionMessage = Assertions.assertThrows(
                BusinessRuleException.class, () -> {
                    verifyMunicipalOrStateRegistrationProviderProduct
                            .verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct1);
                }
        ).getMessage();
        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationProviderProduct
                        .verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct1));
    }
}