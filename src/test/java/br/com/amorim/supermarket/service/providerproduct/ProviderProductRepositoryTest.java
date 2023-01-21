package br.com.amorim.supermarket.service.providerproduct;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidactionexception.InvalidActionException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Esta classe realiza os testes de repositorio da classe ProviderProductCrudServiceImpl
 * Os testes unitários de ProviderProductCrudServiceImpl serão realizados dentro do
 * pacote Service, classe ProviderProductCrudServiceImplTest
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
public class ProviderProductRepositoryTest {

    @Autowired
    private ProviderProductCrudServiceImpl providerProductCrudService;
    @Autowired
    private ProviderProductRepository providerProductRepository;
    @Autowired
    GenerateEntitiesRepositoryUtils generateEntities;

    private ProviderProduct providerProduct1;
    private ProviderProduct providerProduct2;
    private ProviderProduct providerProduct3;
    private ProviderProduct providerProduct4;
    private ProviderProduct providerProduct5;
    private ProviderProduct providerProduct6;
    private ProviderProduct providerProduct7;
    private ProviderProduct providerProduct8;
    private ProviderProduct providerProduct9;
    private ProviderProduct providerProduct10;
    private ProviderProduct providerProduct11;
    private ProviderProduct providerProduct12;
    private ProviderProduct providerProduct13;
    private ProviderProduct providerProduct14;
    private ProviderProduct providerProduct15;
    private ProviderProduct providerProduct16;
    private ProviderProduct providerProduct17;
    private ProviderProduct providerProduct18;
    private ProviderProduct providerProduct19;
    private ProviderProduct providerProduct20;
    private ProviderProduct providerProduct21;

    private void startProvider() {
        providerProduct1 = generateEntities.generateProvider();
        providerProduct2 = generateEntities.generateProvider();
        providerProduct3 = generateEntities.generateProvider();
        providerProduct4 = generateEntities.generateProvider();
        providerProduct5 = generateEntities.generateProvider();
        providerProduct6 = generateEntities.generateProvider();
        providerProduct7 = generateEntities.generateProvider();
        providerProduct8 = generateEntities.generateProvider();
        providerProduct9 = generateEntities.generateProvider();
        providerProduct10 = generateEntities.generateProvider();
        providerProduct11 = generateEntities.generateProvider();
        providerProduct12 = generateEntities.generateProvider();
        providerProduct13 = generateEntities.generateProvider();
        providerProduct14 = generateEntities.generateProvider();
        providerProduct15 = generateEntities.generateProvider();
        providerProduct16 = generateEntities.generateProvider();
        providerProduct17 = generateEntities.generateProvider();
        providerProduct18 = generateEntities.generateProvider();
        providerProduct19 = generateEntities.generateProvider();
        providerProduct20 = generateEntities.generateProvider();
        providerProduct21 = generateEntities.generateProvider();
    }

    @BeforeEach
    public void setUp() {
        this.startProvider();
    }

    private void deleteProvider() {
        providerProductRepository.delete(providerProduct1);
        providerProductRepository.delete(providerProduct2);
        providerProductRepository.delete(providerProduct3);
        providerProductRepository.delete(providerProduct4);
        providerProductRepository.delete(providerProduct5);
        providerProductRepository.delete(providerProduct6);
        providerProductRepository.delete(providerProduct7);
        providerProductRepository.delete(providerProduct8);
        providerProductRepository.delete(providerProduct9);
        providerProductRepository.delete(providerProduct10);
        providerProductRepository.delete(providerProduct11);
        providerProductRepository.delete(providerProduct12);
        providerProductRepository.delete(providerProduct13);
        providerProductRepository.delete(providerProduct14);
        providerProductRepository.delete(providerProduct15);
        providerProductRepository.delete(providerProduct16);
        providerProductRepository.delete(providerProduct17);
        providerProductRepository.delete(providerProduct18);
        providerProductRepository.delete(providerProduct19);
        providerProductRepository.delete(providerProduct20);
        providerProductRepository.delete(providerProduct21);
    }

    @AfterEach
    public void cleanUp() {
        this.deleteProvider();
    }

    @Transactional
    @Test
    void shouldReturnOnlyFifteenProducts() {
        int page = 1;
        int size = 15;
        var getAllPagable = providerProductCrudService.getAll(page, size);

        assertEquals(15, getAllPagable.getNumberOfElements());
    }

    @Transactional
    @Test
    void shouldReturnAMessageErrorWhenTheSizeExceedsTheAllowed() {
        String messageError = getString(MessagesKeyType.COMMON_PAGE_SIZE_INVALID_PAGE_SIZE.message);
        int page = 0;
        int size = 21;

        String exceptionMessage = assertThrows(
                InvalidActionException.class, () ->
                        providerProductCrudService.getAll(page, size)
        ).getMessage();

        assertEquals(messageError, exceptionMessage);
        assertThrows(InvalidActionException.class, () -> providerProductCrudService.getAll(page, size));
    }
}
