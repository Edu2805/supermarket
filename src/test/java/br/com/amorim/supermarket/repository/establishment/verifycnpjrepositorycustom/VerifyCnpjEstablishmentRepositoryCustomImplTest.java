package br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class VerifyCnpjEstablishmentRepositoryCustomImplTest {

    @Autowired
    private VerifyCnpjEstablishmentRepositoryCustomImpl verifyCnpjEstablishmentRepositoryCustom;
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

    @BeforeEach
    void setUp() {
        startEstablishment();
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