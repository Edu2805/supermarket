package br.com.amorim.supermarket.testutils.generateentitiesrepositorytest;

import br.com.amorim.supermarket.common.enums.RoleType;
import br.com.amorim.supermarket.common.enums.ScholarityType;
import br.com.amorim.supermarket.common.enums.SubscriptionType;
import br.com.amorim.supermarket.common.enums.UnityType;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.service.establishment.EstablishmentCrudService;
import br.com.amorim.supermarket.service.person.PersonCrudService;
import br.com.amorim.supermarket.service.providerproduct.ProviderProductCrudService;
import br.com.amorim.supermarket.service.userdata.UserDataService;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCPF;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
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
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private SubSectionRepository subSectionRepository;
    @Autowired
    private ProviderProductCrudService providerProductCrudService;
    @Autowired
    private EstablishmentCrudService establishmentCrudService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private PersonCrudService personCrudService;

    public ProviderProduct generateProvider() {
        Random randomName = new Random();
        Random randomMunicipalRegistration = new Random();
        Random randomStateRegistration = new Random();
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        var name = randomName.nextInt(10000, 19999);
        var municipalRegistration = randomMunicipalRegistration.nextInt(1, 199999);
        var stateRegistration = randomStateRegistration.nextInt(2000, 299999);

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
        Random randomMunicipalRegistration = new Random();
        Random randomStateRegistration = new Random();
        Random randomManager = new Random();
        GenerateCNPJ generateCNPJ = new GenerateCNPJ();
        var name = randomName.nextInt(10000, 19999);
        var municipalRegistration = randomMunicipalRegistration.nextInt(1, 199999);
        var stateRegistration = randomStateRegistration.nextInt(1, 199999);
        var manager = randomManager.nextInt(10000, 19999);

        Establishment establishment = new Establishment();
        establishment.setName(String.valueOf(name));
        establishment.setCnpj(generateCNPJ.cnpj(false));
        establishment.setManager(String.valueOf(manager));
        establishment.setAddress("Avenida Teste, 666, Cidade dos Testes");
        establishment.setMunicipalRegistration(String.valueOf(municipalRegistration));
        establishment.setStateRegistration(String.valueOf(stateRegistration));
        establishment.setPhone("4833333333");
        establishmentCrudService.save(establishment);
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
        productData.setCode(BigInteger.valueOf(code));
        productData.setEan13(String.valueOf(ean13).concat("9999"));
        productData.setInventory(BigDecimal.valueOf(100));
        productData.setProviderProduct(providerProduct);
        productData.setSubSection(subSection);
        productDataRepository.save(productData);
        return productData;
    }

    public Person generatePerson (UserData userData) {
        Random randomFirstName = new Random();
        Random randomMiddleName = new Random();
        Random randomLastName = new Random();
        Random randomRg = new Random();
        Random randomEmail = new Random();

        var firstName = randomFirstName.nextInt(100000, 199999);
        var middleName = randomMiddleName.nextInt(100000, 199999);
        var lastName = randomLastName.nextInt(100000, 199999);
        var rg = randomRg.nextInt(100000, 199999);
        var email = randomEmail.nextInt(100000, 199999);

        Person person = new Person();
        GenerateCPF generateCPF = new GenerateCPF();

        person.setFirstName(String.valueOf(firstName));
        person.setMiddleName(String.valueOf(middleName));
        person.setLastName(String.valueOf(lastName));
        person.setCpf(generateCPF.cpf(false));
        person.setRg(String.valueOf(rg));
        person.setBirthDate(LocalDate.of(1990, 01, 01));
        person.setEmail(String.valueOf(email));
        person.setNaturalness("São José");
        person.setNationality("Brasileiro");
        person.setScholarity(ScholarityType.POSTGRADUATE);
        person.setMotherName("Teste Mãe");
        person.setFatherName("Teste Pai");
        person.setDependents(false);
        person.setUserData(userData);
        personCrudService.save(person);
        return person;
    }

    public UserData generateUserData () {
        Random randomUserName = new Random();
        var usarName = randomUserName.nextInt(10000, 19999);
        UserData userData = new UserData();

        userData.setUserName(String.valueOf(usarName));
        userData.setPassword("123456");
        userData.setIsEmployee(false);
        userData.setRole(RoleType.ADMIN);
        userData.setRegistrationDate(Timestamp.from(Instant.now()));
        userDataService.save(userData);
        return userData;
    }
}
