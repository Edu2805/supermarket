package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Esta classe realiza os testes de repositorio da classe ProductDataCrudServiceImpl
 * Os testes unitários de ProductDataCrudServiceImpl serão realizados dentro do
 * pacote Service, classe ProductDataCrudServiceImplTest
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class ProductDataRepositoryTest {

    @Autowired
    private ProductDataCrudServiceImpl productDataCrudServiceImpl;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    GenerateEntitiesRepositoryUtils generateEntities;

    private ProductData productData1;
    private ProductData productData2;
    private ProductData productData3;
    private ProductData productData4;
    private ProductData productData5;
    private ProductData productData6;

    private void startProduct () {
        var provider = generateEntities.generateProvider();
        var establishment = generateEntities.generateEstablishment();
        var department = generateEntities.generateDepartment(establishment);
        var mainSection = generateEntities.generateMainsection(department);
        var subSection = generateEntities.generateSubsection(mainSection);

        productData1 = generateEntities.generateProductData(provider, subSection);
        productData2 = generateEntities.generateProductData(provider, subSection);
        productData3 = generateEntities.generateProductData(provider, subSection);
        productData4 = generateEntities.generateProductData(provider, subSection);
        productData5 = generateEntities.generateProductData(provider, subSection);
        productData6 = generateEntities.generateProductData(provider, subSection);
    }

    private void deleteProduct() {
        productDataRepository.delete(productData1);
        productDataRepository.delete(productData2);
        productDataRepository.delete(productData3);
        productDataRepository.delete(productData4);
        productDataRepository.delete(productData5);
        productDataRepository.delete(productData6);
    }

    @BeforeEach
    public void setUp() {
        productDataRepository.deleteAll();
        this.startProduct();
    }

    @AfterEach
    public void cleanUp() {
        this.deleteProduct();
    }

    @Transactional
    @Test
    void shouldReturnOnlyTwoProducts() {
        int page = 1;
        int size = 2;
        var getAllPagable = productDataCrudServiceImpl.getAll(page, size);

        assertEquals(2, getAllPagable.getNumberOfElements());
    }

    @Transactional
    @Test
    void shouldReturnAllProducts() {
        int page = 0;
        int size = 20;
        var getAllPagable = productDataCrudServiceImpl.getAll(page, size);

        assertEquals(6, getAllPagable.getNumberOfElements());
    }
}