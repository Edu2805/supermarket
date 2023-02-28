package br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource("classpath:application.properties")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyCnpjEstablishmentRepositoryCustomImplTest {

    @Autowired
    private VerifyCnpjEstablishmentRepositoryCustomImpl verifyCnpjEstablishmentRepositoryCustom;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private SubSectionRepository subSectionRepository;
    @Autowired
    private ProductDataRepository productDataRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;

    private Establishment establishment1;
    private Establishment establishment2;

    private void startEstablishment() {
        establishment1 = generateEntitiesRepository.generateEstablishment();
        establishment2 = new Establishment();
        establishment2.setCnpj(establishment1.getCnpj());
        establishment2.setStateRegistration("1234-CD");
        establishment2.setMunicipalRegistration("4321-DC");
        establishment2.setAddress("Rua Teste, 666");
        establishment2.setPhone("48999999999");
        establishment2.setManager("Senhor dos Testes");
    }

    private void deleteEstablishment() {
        establishmentRepository.delete(establishment1);
        establishmentRepository.delete(establishment2);
    }

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        establishmentRepository.deleteAll();
        departmentRepository.deleteAll();
        mainSectionRepository.deleteAll();
        subSectionRepository.deleteAll();
        productDataRepository.deleteAll();
        startEstablishment();
    }

    @AfterEach
    void cleanUp() {
        deleteEstablishment();
    }

    @Transactional
    @Test
    void shouldReturnTrueIfExistsByCnpj() {
        var existsByCnpj = verifyCnpjEstablishmentRepositoryCustom.existsByCnpj(establishment2);
        assertTrue(existsByCnpj);
    }

    @Transactional
    @Test
    void shouldReturnFalseIfNotExistsByCnpj() {
        establishment1.setCnpj("22519797000170");
        var notExistsByCnpj = verifyCnpjEstablishmentRepositoryCustom.existsByCnpj(establishment1);
        assertFalse(notExistsByCnpj);
    }

    @Transactional
    @Test
    void shouldReturnFalseWhenIdAndCnpjIsNullInTheQuery() {
        establishment1.setId(null);
        establishment1.setCnpj(null);
        var notExistsByCnpj = verifyCnpjEstablishmentRepositoryCustom.existsByCnpj(establishment1);
        assertFalse(notExistsByCnpj);
    }
}