package br.com.amorim.supermarket.repository.providerproduct.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigInteger;

@TestPropertySource("classpath:application.properties")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class GenerateInternalCodeProviderProductRepositoryCustomImplTest {

    @Autowired
    private GenerateInternalCodeProviderProductRepositoryCustomImpl generateInternalCodeProviderProductRepositoryCustom;
    @Autowired
    private ProviderProductRepository providerProductRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntities;
    @Autowired
    private ProductDataRepository productDataRepository;

    private ProviderProduct providerProduct1;
    private ProviderProduct providerProduct2;
    private ProviderProduct providerProduct3;

    private void startProvide () {
        providerProduct1 = generateEntities.generateProvider();
        providerProduct2 = generateEntities.generateProvider();
        providerProduct3 = generateEntities.generateProvider();
    }

    private void deleteProvide () {
        providerProductRepository.delete(providerProduct1);
        providerProductRepository.delete(providerProduct2);
        providerProductRepository.delete(providerProduct3);
    }

    @BeforeEach
    void setUp() {
        productDataRepository.deleteAll();
        providerProductRepository.deleteAll();
        startProvide();
    }

    @AfterEach
    void cleanUp() {
        deleteProvide();
    }

    @Transactional
    @Test
    void shouldSetTheFirstProvideWithTheInternalCodeEqualToOne() {
        Assertions.assertEquals(BigInteger.valueOf(1), providerProduct1.getCode());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        Assertions.assertEquals(BigInteger.valueOf(3), providerProduct3.getCode());
    }
}