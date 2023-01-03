package br.com.amorim.supermarket.repository.productdata.productdatarepositorycustom;

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
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= SupermarketApplication.class)
public class ProductDataRepositoryCustomTest {

    @Autowired
    private ProductDataRepositoryCustomImpl productDataRepositoryCustom;
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
    private ProductDataReposotiry productDataReposotiry;

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
        productDataReposotiry.save(productData1);

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
        productDataReposotiry.save(productData2);

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

    @Before
    public void setUp() {
        this.startProduct();
    }

    @Test
    public void shouldGenerateASequenceInternalCode () {
        var internalCodeGenerated = productDataRepositoryCustom.generateInternalCode(productData3);
        productData3.setInternalCode(internalCodeGenerated);

        productDataReposotiry.save(productData3);

        assertEquals(BigInteger.valueOf(3), internalCodeGenerated);
    }

    public ProviderProduct generateProvider() {
        String NAME = "Fornecedor teste";
        ProviderProduct providerProduct = new ProviderProduct();
        providerProduct.setCode(BigInteger.ONE);
        providerProduct.setName(NAME);
        providerProduct.setPhone("48999999999");
        providerProduct.setAddress("Avenida dos Testes, 666, Cidade dos Testes");
        providerProduct.setMunicipalRegistration("12345-A");
        providerProduct.setStateRegistration("54321-B");
        providerProduct.setResponsible("Senhor Teste");
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber("12345678000112");
        providerProductRepository.save(providerProduct);
        return providerProduct;
    }

    public Establishment generateEstablishment () {
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        Random rand = new Random();
        BigInteger generateMunicipalRegistration = BigInteger.valueOf(rand.nextInt(100));
        BigInteger generateStateRegistration = BigInteger.valueOf(rand.nextInt(101, 200));
        BigInteger generateManagerName = BigInteger.valueOf(rand.nextInt(201, 300));

        String NAME = "Loja teste";
        Establishment establishment = new Establishment();
        establishment.setName(NAME);
        establishment.setCode(BigInteger.valueOf(rand.nextLong()));
        establishment.setCnpj(generateCNPJ.cnpj(false));
        establishment.setManager(generateManagerName.toString());
        establishment.setAddress("Avenida Teste, 666, Cidade dos Testes");
        establishment.setMunicipalRegistration(generateMunicipalRegistration.toString());
        establishment.setStateRegistration(generateStateRegistration.toString());
        establishment.setPhone("4833333333");
        establishmentRepository.save(establishment);
        return establishment;
    }

    public Department generateDepartment (Establishment establishment) {
        String NAME = "Departamento teste";
        Department department = new Department();
        department.setName(NAME);
        department.setCode(BigInteger.ONE);
        department.setEstablishment(establishment);
        departmentRepository.save(department);
        return department;
    }

    public MainSection generateMainsection (Department department) {
        String NAME = "Seção principal teste";
        MainSection mainSection = new MainSection();
        mainSection.setName(NAME);
        mainSection.setCode(BigInteger.ONE);
        mainSection.setDepartment(department);
        mainSectionRepository.save(mainSection);
        return mainSection;
    }

    public SubSection generateSubsection (MainSection mainSection) {
        String NAME = "Sub Seção Teste";
        SubSection subSection = new SubSection();
        subSection.setName(NAME);
        subSection.setCode(BigInteger.ONE);
        subSection.setMainSection(mainSection);
        subSectionRepository.save(subSection);
        return subSection;
    }
}