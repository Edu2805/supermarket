package br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Random;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyMunicipalOrStateRegistrationCustomImplTest {

    @Autowired
    private VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustomImpl verifyMunicipalOrStateRegistrationCustom;
    @Autowired
    private ProviderProductRepository providerProductRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntities;

    private static String STATE_REGISTRATION = "1234-C";
    private static String MUNICIPAL_REGISTRATION = "1234-C";

    private ProviderProduct providerProduct1;
    private ProviderProduct providerProduct2;

    private void startProvide () {
        Random randomName = new Random();
        Random randomMunicipalRegistration = new Random();
        Random randomStateRegistration = new Random();
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        var name = randomName.nextInt(10000, 19999);
        var municipalRegistration = randomMunicipalRegistration.nextInt(1, 1999);
        var stateRegistration = randomStateRegistration.nextInt(2000, 2999);

        providerProduct1 = new ProviderProduct();
        providerProduct1.setStateRegistration(STATE_REGISTRATION);
        providerProduct1.setMunicipalRegistration(MUNICIPAL_REGISTRATION);
        providerProduct1 = generateEntities.generateProvider();

        providerProduct2 = new ProviderProduct();
        providerProduct2.setName(String.valueOf(name));
        providerProduct2.setPhone("48999999999");
        providerProduct2.setAddress("Avenida dos Testes, 666, Cidade dos Testes");
        providerProduct2.setMunicipalRegistration(String.valueOf(municipalRegistration));
        providerProduct2.setStateRegistration(String.valueOf(stateRegistration));
        providerProduct2.setResponsible("Senhor Teste");
        providerProduct2.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct2.setSubscriptionNumber(generateCNPJ.cnpj(false));
    }

    private void deleteProvide () {
        providerProductRepository.delete(providerProduct1);
        providerProductRepository.delete(providerProduct2);
    }

    @BeforeEach
    void setUp() {
        startProvide();
    }

    @AfterEach
    void cleanUp() {
        deleteProvide();
    }

    @Test
    void shouldShowAMessageErrorWhenMunicipalRegistrationAlreadyExistsInDatabase() {
        String messageError = getString(MessagesKeyType.PROVIDER_PRODUCT_MUNICIPAL_REGISTER_ALREADY_EXISTS.message);
        providerProduct2.setMunicipalRegistration(providerProduct1.getMunicipalRegistration());

        String exceptionMessage = assertThrows(
                BusinessRuleException.class, () ->
                        verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct2)
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct2));
    }

    @Test
    void shouldShowAMessageErrorWhenStateRegistrationAlreadyExistsInDatabase() {
        String messageError = getString(MessagesKeyType.PROVIDER_PRODUCT_STATE_REGISTER_ALREADY_EXISTS.message);
        providerProduct2.setStateRegistration(providerProduct1.getStateRegistration());

        String exceptionMessage = assertThrows(
                BusinessRuleException.class, () ->
                        verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct2)
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(BusinessRuleException.class, () ->
                verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct2));
    }

    @Test
    void shouldReturnFalseWhenStateRegistrationIsNull() {
        providerProduct2.setStateRegistration(null);

        var verify = verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct2);
        assertFalse(verify);
    }

    @Test
    void shouldReturnFalseWhenMunicipalRegistrationIsNull() {
        providerProduct2.setMunicipalRegistration(null);

        var verify = verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct2);
        assertFalse(verify);
    }

    @Test
    void shouldSaveWithSuccess() {
        var verify = verifyMunicipalOrStateRegistrationCustom.existsByMunicipalOrStateRegistration(providerProduct2);
        assertFalse(verify);
    }
}