package br.com.amorim.supermarket.testutils.generateentitiesrepositorytest;

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
import br.com.amorim.supermarket.service.providerproduct.ProviderProductCrudService;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@AllArgsConstructor

@Component
public class GenerateEntitiesRepositoryUtils {

    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private ProviderProductCrudService providerProductCrudService;
    @Autowired
    private SubSectionRepository subSectionRepository;

    public ProviderProduct generateProvider() {
        Random randomName = new Random();
        Random randomMunicipalRegistration = new Random();
        Random randomStateRegistration = new Random();
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        var name = randomName.nextInt(10000, 19999);
        var municipalRegistration = randomMunicipalRegistration.nextInt(1, 1999);
        var stateRegistration = randomStateRegistration.nextInt(1, 1999);

        ProviderProduct providerProduct = new ProviderProduct();
        providerProduct.setName(String.valueOf(name));
        providerProduct.setPhone("48999999999");
        providerProduct.setAddress("Avenida dos Testes, 666, Cidade dos Testes");
        providerProduct.setMunicipalRegistration(String.valueOf(municipalRegistration));
        providerProduct.setStateRegistration(String.valueOf(stateRegistration));
        providerProduct.setResponsible("Senhor Teste");
        providerProduct.setSubscriptionType(SubscriptionType.CNPJ);
        providerProduct.setSubscriptionNumber(generateCNPJ.cnpj(false));
        providerProductCrudService.save(providerProduct);
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

    public ProductData generateProductData (ProviderProduct providerProduct, SubSection subSection) {
        Random randomName = new Random();
        Random randomCode = new Random();
        Random randomEan13 = new Random();
        UUID uuid = randomUUID();

        var name = randomName.nextInt(10000, 19999);
        var code = randomCode.nextInt(1, 19999);
        var ean13 = randomEan13.nextInt(789000000, 789999999);

        ProductData productData = new ProductData();
        productData.setId(uuid);
        productData.setName(String.valueOf(name));
        productData.setUnity(UnityType.UNITY);
        productData.setPurchasePrice(BigDecimal.valueOf(10.90));
        productData.setSalePrice(BigDecimal.valueOf(15.90));
        productData.setMargin(BigDecimal.valueOf(0.5555));
        productData.setInternalCode(BigInteger.valueOf(code));
        productData.setEan13(String.valueOf(ean13).concat("9999"));
        productData.setInventory(BigDecimal.valueOf(100));
        productData.setProviderProduct(providerProduct);
        productData.setSubSection(subSection);
        productDataRepository.save(productData);
        return productData;
    }
}
