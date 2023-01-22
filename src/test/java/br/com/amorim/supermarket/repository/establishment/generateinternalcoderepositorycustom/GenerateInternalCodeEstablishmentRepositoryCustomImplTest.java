package br.com.amorim.supermarket.repository.establishment.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class GenerateInternalCodeEstablishmentRepositoryCustomImplTest {

    @Autowired
    private GenerateInternalCodeEstablishmentRepositoryCustomImpl generateInternalCodeEstablishmentRepositoryCustom;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;

    private Establishment establishment1;
    private Establishment establishment2;
    private Establishment establishment3;

    private void startProvide () {
        establishment1 = generateEntitiesRepository.generateEstablishment();
        establishment2 = generateEntitiesRepository.generateEstablishment();
        establishment3 = generateEntitiesRepository.generateEstablishment();
    }

    private void deleteProvide () {
        establishmentRepository.delete(establishment1);
        establishmentRepository.delete(establishment2);
        establishmentRepository.delete(establishment3);
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
    void shouldSetTheFirstEstablishmentWithTheInternalCodeEqualToOne() {
        assertEquals(BigInteger.valueOf(1), establishment1.getCode());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        assertEquals(BigInteger.valueOf(3), establishment3.getCode());
    }
}