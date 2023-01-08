package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class ProductDataCrudServiceImplTest {

    @Autowired
    private ProductDataCrudServiceImpl productDataCrudServiceImpl;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private ProviderProductRepository providerProductRepository;
    @Autowired
    private SubSectionRepository subSectionRepository;

    private ProductData productData1;
    private ProductData productData2;
    private ProductData productData3;
    private ProductData productData4;
    private ProductData productData5;
    private ProductData productData6;
    public static final java.util.UUID UUID_1 = randomUUID();
    public static final String NAME_1 = "Produto teste 1";
    public static final String NAME_2 = "Produto teste 2";
    public static final String NAME_3 = "Produto teste 3";
    public static final String NAME_4 = "Produto teste 4";
    public static final String NAME_5 = "Produto teste 5";
    public static final String NAME_6 = "Produto teste 6";

    private void startProduct () {
        Random randomEan13Product1 = new Random();
        Random randomDun14Product2 = new Random();
        Random randomDun14Product3 = new Random();
        Random randomDun14Product4 = new Random();
        Random randomDun13Product5 = new Random();
        Random randomDun14Product6 = new Random();
        Random randomCode1 = new Random();
        Random randomCode2 = new Random();
        Random randomCode3 = new Random();
        Random randomCode4 = new Random();
        Random randomCode5 = new Random();
        Random randomCode6 = new Random();

        var generateProvider = this.generateProvider();
        var generateEstablishment = this.generateEstablishment();
        var generateDepartment = this.generateDepartment(generateEstablishment);
        var generateMainSection = this.generateMainsection(generateDepartment);
        var generateSubsection = this.generateSubsection(generateMainSection);
        var ean13Product1 = randomEan13Product1.nextInt(789000000, 789999999);
        var ean13Product2 = randomDun14Product2.nextInt(799000000, 799999999);
        var dun14Product3 = randomDun14Product3.nextInt(889000000, 889999999);
        var dun14Product4 = randomDun14Product4.nextInt(899000000, 899999999);
        var ean13Product5 = randomDun13Product5.nextInt(900000000, 999999999);
        var dun14Product6 = randomDun14Product6.nextInt(110000000, 119999999);
        var code1 = randomCode1.nextInt(1, 1000);
        var code2 = randomCode2.nextInt(1001, 2000);
        var code3 = randomCode3.nextInt(2001, 3000);
        var code4 = randomCode4.nextInt(3001, 4000);
        var code5 = randomCode5.nextInt(4001, 5000);
        var code6 = randomCode6.nextInt(5001, 6000);

        productData1 = new ProductData();
        productData1.setName(NAME_1);
        productData1.setUnity(UnityType.UNITY);
        productData1.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData1.setSalePrice(BigDecimal.valueOf(15.90));
        productData1.setMargin(BigDecimal.valueOf(0.9999));
        productData1.setEan13(String.valueOf(ean13Product1).concat("9999"));
        productData1.setInternalCode(BigInteger.valueOf(code1));
        productData1.setInventory(BigDecimal.valueOf(100));
        productData1.setProviderProduct(generateProvider);
        productData1.setSubSection(generateSubsection);
        productDataRepository.save(productData1);

        productData2 = new ProductData();
        productData2.setName(NAME_2);
        productData2.setUnity(UnityType.UNITY);
        productData2.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData2.setSalePrice(BigDecimal.valueOf(15.90));
        productData2.setMargin(BigDecimal.valueOf(0.9999));
        productData2.setEan13(String.valueOf(ean13Product2).concat("9999"));
        productData2.setInternalCode(BigInteger.valueOf(code2));
        productData2.setInventory(BigDecimal.valueOf(100));
        productData2.setProviderProduct(generateProvider);
        productData2.setSubSection(generateSubsection);
        productDataRepository.save(productData2);

        productData3 = new ProductData();
        productData3.setName(NAME_3);
        productData3.setUnity(UnityType.UNITY);
        productData3.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData3.setSalePrice(BigDecimal.valueOf(15.90));
        productData3.setMargin(BigDecimal.valueOf(0.9999));
        productData3.setDun14(String.valueOf(dun14Product3).concat("99999"));
        productData3.setInternalCode(BigInteger.valueOf(code3));
        productData3.setInventory(BigDecimal.valueOf(100));
        productData3.setProviderProduct(generateProvider);
        productData3.setSubSection(generateSubsection);
        productDataRepository.save(productData3);

        productData4 = new ProductData();
        productData4.setName(NAME_4);
        productData4.setUnity(UnityType.UNITY);
        productData4.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData4.setSalePrice(BigDecimal.valueOf(15.90));
        productData4.setMargin(BigDecimal.valueOf(0.9999));
        productData4.setDun14(String.valueOf(dun14Product4).concat("99999"));
        productData4.setInternalCode(BigInteger.valueOf(code4));
        productData4.setInventory(BigDecimal.valueOf(100));
        productData4.setProviderProduct(generateProvider);
        productData4.setSubSection(generateSubsection);
        productDataRepository.save(productData4);

        productData5 = new ProductData();
        productData5.setName(NAME_5);
        productData5.setUnity(UnityType.UNITY);
        productData5.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData5.setSalePrice(BigDecimal.valueOf(15.90));
        productData5.setMargin(BigDecimal.valueOf(0.9999));
        productData5.setEan13(String.valueOf(ean13Product5).concat("9999"));
        productData5.setInternalCode(BigInteger.valueOf(code5));
        productData5.setInventory(BigDecimal.valueOf(100));
        productData5.setProviderProduct(generateProvider);
        productData5.setSubSection(generateSubsection);
        productDataRepository.save(productData5);

        productData6 = new ProductData();
        productData6.setName(NAME_6);
        productData6.setUnity(UnityType.UNITY);
        productData6.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData6.setSalePrice(BigDecimal.valueOf(15.90));
        productData6.setMargin(BigDecimal.valueOf(0.9999));
        productData6.setDun14(String.valueOf(dun14Product6).concat("99999"));
        productData6.setInternalCode(BigInteger.valueOf(code6));
        productData6.setInventory(BigDecimal.valueOf(100));
        productData6.setProviderProduct(generateProvider);
        productData6.setSubSection(generateSubsection);
        productDataRepository.save(productData6);
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

    public ProviderProduct generateProvider() {
        Random randomName = new Random();
        Random randomCode = new Random();
        Random randomMunicipalRegistration = new Random();
        Random randomStateRegistration = new Random();
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);
        var municipalRegistration = randomMunicipalRegistration.nextInt(1, 1999);
        var stateRegistration = randomStateRegistration.nextInt(1, 1999);

        ProviderProduct providerProduct = new ProviderProduct();
        providerProduct.setCode(BigInteger.valueOf(code));
        providerProduct.setName(String.valueOf(name));
        providerProduct.setPhone("48999999999");
        providerProduct.setAddress("Avenida dos Testes, 666, Cidade dos Testes");
        providerProduct.setMunicipalRegistration(String.valueOf(municipalRegistration));
        providerProduct.setStateRegistration(String.valueOf(stateRegistration));
        providerProduct.setResponsible("Senhor Teste");
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber(generateCNPJ.cnpj(false));
        providerProductRepository.save(providerProduct);
        return providerProduct;
    }

    public Establishment generateEstablishment () {
        Random randomName = new Random();
        Random randomCode = new Random();
        Random randomMunicipalRegistration = new Random();
        Random randomStateRegistration = new Random();
        Random randomManager = new Random();
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);
        var municipalRegistration = randomMunicipalRegistration.nextInt(1, 1999);
        var stateRegistration = randomStateRegistration.nextInt(1, 1999);
        var manager = randomManager.nextInt(10000, 19999);

        Establishment establishment = new Establishment();
        establishment.setName(String.valueOf(name));
        establishment.setCode(BigInteger.valueOf(code));
        establishment.setCnpj(generateCNPJ.cnpj(false));
        establishment.setManager(String.valueOf(manager));
        establishment.setAddress("Avenida Teste, 666, Cidade dos Testes");
        establishment.setMunicipalRegistration(String.valueOf(municipalRegistration));
        establishment.setStateRegistration(String.valueOf(stateRegistration));
        establishment.setPhone("4833333333");
        establishmentRepository.save(establishment);
        return establishment;
    }

    public Department generateDepartment (Establishment establishment) {
        Random randomName = new Random();
        Random randomCode = new Random();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);

        Department department = new Department();
        department.setName(String.valueOf(name));
        department.setCode(BigInteger.valueOf(code));
        department.setEstablishment(establishment);
        departmentRepository.save(department);
        return department;
    }

    public MainSection generateMainsection (Department department) {
        Random randomName = new Random();
        Random randomCode = new Random();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);

        MainSection mainSection = new MainSection();
        mainSection.setName(String.valueOf(name));
        mainSection.setCode(BigInteger.valueOf(code));
        mainSection.setDepartment(department);
        mainSectionRepository.save(mainSection);
        return mainSection;
    }

    public SubSection generateSubsection (MainSection mainSection) {
        Random randomName = new Random();
        Random randomCode = new Random();
        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);

        SubSection subSection = new SubSection();
        subSection.setName(String.valueOf(name));
        subSection.setCode(BigInteger.valueOf(code));
        subSection.setMainSection(mainSection);
        subSectionRepository.save(subSection);
        return subSection;
    }
}