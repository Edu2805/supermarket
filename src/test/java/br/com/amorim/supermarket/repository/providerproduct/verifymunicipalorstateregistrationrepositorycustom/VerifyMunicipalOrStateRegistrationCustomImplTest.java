package br.com.amorim.supermarket.repository.providerproduct.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class VerifyMunicipalOrStateRegistrationCustomImplTest {

    @Autowired
    private VerifyMunicipalOrStateRegistrationProviderProductRepositoryCustomImpl verifyMunicipalOrStateRegistrationCustom;
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

    @BeforeEach
    void setUp() {
        startProvide();
    }

    @Transactional
    @Test
    void shouldReturnOneWhenMunicipalRegistrationAlreadyExistsInDatabase() {
        providerProduct2.setMunicipalRegistration(providerProduct1.getMunicipalRegistration());
        assertEquals(1, verifyMunicipalOrStateRegistrationCustom
                .existsByMunicipalOrStateRegistration(providerProduct2));
    }

    @Transactional
    @Test
    void shouldReturnTwoWhenStateRegistrationAlreadyExistsInDatabase() {
        providerProduct2.setStateRegistration(providerProduct1.getStateRegistration());
        assertEquals(2, verifyMunicipalOrStateRegistrationCustom
                .existsByMunicipalOrStateRegistration(providerProduct2));
    }

    @Transactional
    @Test
    void shouldReturnThreeAndSaveWithSuccess() {
        assertEquals(3, verifyMunicipalOrStateRegistrationCustom
                .existsByMunicipalOrStateRegistration(providerProduct2));
    }

    @Transactional
    @Test
    void shouldReturnThreeWhenIdAndMunicipalRegistrationIsNull() {
        providerProduct2.setId(null);
        providerProduct2.setMunicipalRegistration(null);
        assertEquals(3, verifyMunicipalOrStateRegistrationCustom
                .existsByMunicipalOrStateRegistration(providerProduct2));
    }

    @Transactional
    @Test
    void shouldReturnThreeWhenIdAndStateRegistrationIsNull() {
        providerProduct2.setId(null);
        providerProduct2.setStateRegistration(null);
        assertEquals(3, verifyMunicipalOrStateRegistrationCustom
                .existsByMunicipalOrStateRegistration(providerProduct2));
    }
}