package br.com.amorim.supermarket.repository.establishment.verifymunicipalorstateregistrationrepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.service.establishment.EstablishmentCrudService;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCNPJ;
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

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("classpath:application.properties")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustomImplTest {

    @Autowired
    private VerifyMunicipalOrStateRegistrationEstablishmentRepositoryCustomImpl verifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;
    @Autowired
    private EstablishmentCrudService establishmentCrudService;
    private GenerateCNPJ generateCNPJ1;

    private Establishment establishment1;
    private Establishment establishment2;

    private void startProvide () {
        generateCNPJ1 = new GenerateCNPJ();
        establishment1 = new Establishment();
        establishment1.setName("Loja Teste 1");
        establishment1.setCnpj(generateCNPJ1.cnpj(false));
        establishment1.setStateRegistration("1234-AB");
        establishment1.setMunicipalRegistration("4321-BA");
        establishment1.setAddress("Rua Teste, 666");
        establishment1.setPhone("48999999999");
        establishment1.setManager("Senhor dos Testes");
        establishmentCrudService.save(establishment1);

        establishment2 = new Establishment();
        establishment2.setName("Loja Teste 2");
        establishment2.setCnpj(generateCNPJ1.cnpj(false));
        establishment2.setStateRegistration("1234-CD");
        establishment2.setMunicipalRegistration("4321-DC");
        establishment2.setAddress("Rua Teste, 666");
        establishment2.setPhone("48999999999");
        establishment2.setManager("Senhor dos Testes");
    }

    private void deleteProvide () {
        establishmentRepository.delete(establishment1);
        establishmentRepository.delete(establishment2);
    }

    @BeforeEach
    void setUp() {
        startProvide();
    }

    @AfterEach
    void cleanUp() {
        deleteProvide();
    }

    @Transactional
    @Test
    void shouldReturnOneIfExistsByMunicipalRegistration() {
        establishment2.setId(null);
        establishment2.setMunicipalRegistration(establishment1.getMunicipalRegistration());
        var existsByMunicipalRegistration = verifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment2);
        assertEquals(1, existsByMunicipalRegistration);
    }

    @Transactional
    @Test
    void shouldReturnTwoIfExistsByStateRegistration() {
        establishment2.setId(null);
        establishment2.setStateRegistration(establishment1.getStateRegistration());
        var existsByStateRegistration = verifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment2);
        assertEquals(2, existsByStateRegistration);
    }

    @Transactional
    @Test
    void shouldReturnThreeIfNotExistsByStateRegistration() {
        establishment2.setId(null);
        var existsByStateRegistration = verifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment2);
        assertEquals(3, existsByStateRegistration);
    }

    @Transactional
    @Test
    void shouldReturnThreeWhenIdAndMunicipalRegistrationIsNullInTheQuery() {
        establishment2.setId(null);
        establishment2.setMunicipalRegistration(null);
        var existsByMunicipalRegistration = verifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment2);
        assertEquals(3, existsByMunicipalRegistration);
    }

    @Transactional
    @Test
    void shouldReturnThreeWhenIdAndStateRegistrationIsNullInTheQuery() {
        establishment2.setId(null);
        establishment2.setStateRegistration(null);
        var existsByStateRegistration = verifyMunicipalOrStateRegistrationEstablishmentRepositoryCustom
                .existsByMunicipalOrStateRegistration(establishment2);
        assertEquals(3, existsByStateRegistration);
    }
}