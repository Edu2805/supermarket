package br.com.amorim.supermarket.repository.productdata.generateinternalcoderepositorycustom;

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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class ProductDataRepositoryCustomTest {

    @Autowired
    private GenerateInternalCodeRepositoryCustom generateInternalCodeRepositoryCustom;
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
    @Autowired
    private ProductDataRepository productDataRepository;

    private ProductData productData1;
    private ProductData productData2;
    private ProductData productData3;
    public static final java.util.UUID UUID_1 = randomUUID();
    public static final String NAME_1 = "Produto teste";
    public static final java.util.UUID UUID_2 = randomUUID();
    public static final String NAME_2 = "Produto teste";
    public static final java.util.UUID UUID_3 = randomUUID();
    public static final String NAME_3 = "Produto teste";

    private void startProduct () {
        var generateProvider = this.generateProvider();
        var generateEstablishment = this.generateEstablishment();
        var generateDepartment = this.generateDepartment(generateEstablishment);
        var generateMainSection = this.generateMainsection(generateDepartment);
        var generateSubsection = this.generateSubsection(generateMainSection);

        productData1 = new ProductData();
        productData1.setId(UUID_1);
        productData1.setName(NAME_1);
        productData1.setUnity(UnityType.UNITY);
        productData1.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData1.setSalePrice(BigDecimal.valueOf(15.90));
        productData1.setEan13("7891112223334");
        productData1.setInternalCode(BigInteger.valueOf(1));
        productData1.setInventory(BigDecimal.valueOf(100));
        productData1.setProviderProduct(generateProvider);
        productData1.setSubSection(generateSubsection);
        productDataRepository.save(productData1);

        productData2 = new ProductData();
        productData2.setId(UUID_2);
        productData2.setName(NAME_2);
        productData2.setUnity(UnityType.UNITY);
        productData2.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData2.setSalePrice(BigDecimal.valueOf(15.90));
        productData2.setEan13("7891112223335");
        productData2.setInternalCode(BigInteger.valueOf(2));
        productData2.setInventory(BigDecimal.valueOf(100));
        productData2.setProviderProduct(generateProvider);
        productData2.setSubSection(generateSubsection);
        productDataRepository.save(productData2);

        productData3 = new ProductData();
        productData3.setId(UUID_3);
        productData3.setName(NAME_3);
        productData3.setUnity(UnityType.UNITY);
        productData3.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData3.setSalePrice(BigDecimal.valueOf(15.90));
        productData3.setEan13("7891112223336");
        productData3.setInventory(BigDecimal.valueOf(100));
        productData3.setProviderProduct(generateProvider);
        productData3.setSubSection(generateSubsection);
    }

    private void deleteProduct() {
        productDataRepository.delete(productData1);
        productDataRepository.delete(productData2);
        productDataRepository.delete(productData3);
    }

    @BeforeEach
    public void setUp() {
        this.startProduct();
    }

    @AfterEach
    public void cleanUp() {
        this.deleteProduct();
    }

    @Test void shouldGenerateASequenceInternalCode () {
        var internalCodeGenerated = generateInternalCodeRepositoryCustom.generateInternalCode(productData3);
        productData3.setInternalCode(internalCodeGenerated);

        productDataRepository.save(productData3);

        assertEquals(BigInteger.valueOf(3), internalCodeGenerated);
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