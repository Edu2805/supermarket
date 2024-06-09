package br.com.amorim.supermarket.repository.establishment.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY)
@ActiveProfiles("test")
class GenerateInternalCodeEstablishmentRepositoryCustomImplTest {

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

    @BeforeEach
    void setUp() {
        startProvide();
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