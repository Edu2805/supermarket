package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class ProductDataCrudServiceImplTest {

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
        this.startProduct();
    }

    @AfterEach
    public void cleanUp() {
        this.deleteProduct();
    }

    @Test
    void shouldReturnOnlyTwoProducts() {
        int page = 1;
        int size = 2;
        var getAllPagable = productDataCrudServiceImpl.getAll(page, size);

        assertEquals(2, getAllPagable.getNumberOfElements());
    }

    @Test
    void shouldReturnAllProducts() {
        int page = 0;
        int size = 20;
        var getAllPagable = productDataCrudServiceImpl.getAll(page, size);

        assertEquals(12, getAllPagable.getNumberOfElements());
    }
}