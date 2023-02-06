package br.com.amorim.supermarket.repository.providerproduct.verifysubscriptionnumberrepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifySubscriptionNumberProviderProductRepositoryCustomImplTest {

    @Autowired
    private VerifySubscriptionNumberProviderProductRepositoryCustomImpl verifySubscriptionNumberProviderProductRepositoryCustom;
    @Autowired
    private ProviderProductRepository providerProductRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntities;

    private GenerateCNPJ generateCNPJ;
    private ProviderProduct providerProduct1;
    private ProviderProduct providerProduct2;

    private void startProvide () {
        generateCNPJ = new GenerateCNPJ();

        providerProduct1 = new ProviderProduct();
        providerProduct1.setName("Fornecedor teste 1");
        providerProduct1.setCode(BigInteger.valueOf(1));
        providerProduct1.setPhone("48999999999");
        providerProduct1.setAddress("Avenida dos Testes, 666, Cidade dos Testes");
        providerProduct1.setMunicipalRegistration("2425-AB");
        providerProduct1.setStateRegistration("5757-BA");
        providerProduct1.setResponsible("Senhor Teste");
        providerProduct1.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct1.setSubscriptionNumber(generateCNPJ.cnpj(false));
        providerProductRepository.save(providerProduct1);

        providerProduct2 = new ProviderProduct();
        providerProduct2.setName("Fornecedor teste 1");
        providerProduct2.setCode(BigInteger.valueOf(1));
        providerProduct2.setPhone("48999999999");
        providerProduct2.setAddress("Avenida dos Testes, 666, Cidade dos Testes");
        providerProduct2.setMunicipalRegistration("2425-CD");
        providerProduct2.setStateRegistration("5757-DC");
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

    @Transactional
    @Test
    void shouldReturnTrueWhenIdIsNullAndSubscriptionNumberAlreadyExistsInDatabase() {
        providerProduct2.setId(null);
        providerProduct2.setSubscriptionNumber(providerProduct1.getSubscriptionNumber());
        assertTrue(verifySubscriptionNumberProviderProductRepositoryCustom
                .existsBySubscriptionNumber(providerProduct2));
    }

    @Transactional
    @Test
    void shouldReturnFalseWhenSubscriptionNumberAlreadyExistsInDatabase() {
        providerProduct2.setId(UUID.randomUUID());
        providerProduct2.setSubscriptionNumber(providerProduct1.getSubscriptionNumber());
        assertFalse(verifySubscriptionNumberProviderProductRepositoryCustom
                .existsBySubscriptionNumber(providerProduct2));
    }
    @Transactional
    @Test
    void shouldReturnFalseWhenIdAndSubscriptionNumberAreNullInTheQuery() {
        providerProduct2.setId(null);
        providerProduct2.setSubscriptionNumber(null);
        assertFalse(verifySubscriptionNumberProviderProductRepositoryCustom
                .existsBySubscriptionNumber(providerProduct2));
    }
}