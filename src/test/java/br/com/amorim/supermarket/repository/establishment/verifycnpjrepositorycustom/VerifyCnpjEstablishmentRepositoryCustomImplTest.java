package br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCNPJ;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyCnpjEstablishmentRepositoryCustomImplTest {

    @Autowired
    private VerifyCnpjEstablishmentRepositoryCustomImpl verifyCnpjEstablishmentRepositoryCustom;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;
    private GenerateCNPJ generateCNPJ1;

    private Establishment establishment1;
    private Establishment establishment2;

    private void startProvide () {
        generateCNPJ1 = new GenerateCNPJ();
        establishment1 = new Establishment();
        establishment1.setName("Loja Teste 1");
        establishment1.setCode(BigInteger.valueOf(1));
        establishment1.setCnpj(generateCNPJ1.cnpj(false));
        establishment1.setStateRegistration("1234-AB");
        establishment1.setMunicipalRegistration("4321-BA");
        establishment1.setAddress("Rua Teste, 666");
        establishment1.setPhone("48999999999");
        establishment1.setManager("Senhor dos Testes");
        establishmentRepository.save(establishment1);

        establishment2 = new Establishment();
        establishment2.setName("Loja Teste 2");
        establishment2.setCode(BigInteger.valueOf(2));
        establishment2.setCnpj(establishment1.getCnpj());
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