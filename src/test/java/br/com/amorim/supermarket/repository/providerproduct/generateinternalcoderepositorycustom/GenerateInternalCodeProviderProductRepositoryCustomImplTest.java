package br.com.amorim.supermarket.repository.providerproduct.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class GenerateInternalCodeProviderProductRepositoryCustomImplTest {

    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntities;

    private ProviderProduct providerProduct1;
    private ProviderProduct providerProduct2;
    private ProviderProduct providerProduct3;

    private void startProvide () {
        providerProduct1 = generateEntities.generateProvider();
        providerProduct2 = generateEntities.generateProvider();
        providerProduct3 = generateEntities.generateProvider();
    }

    @BeforeEach
    void setUp() {
        startProvide();
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