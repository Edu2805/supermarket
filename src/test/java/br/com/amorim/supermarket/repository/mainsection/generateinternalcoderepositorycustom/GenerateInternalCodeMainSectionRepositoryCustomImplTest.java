package br.com.amorim.supermarket.repository.mainsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class GenerateInternalCodeMainSectionRepositoryCustomImplTest {

    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepositoryUtils;
    private MainSection mainSection1;
    private MainSection mainSection2;
    private MainSection mainSection3;
    private Department department;
    private Establishment establishment;

    private void startMainSection() {
        establishment = generateEntitiesRepositoryUtils.generateEstablishment();
        department = generateEntitiesRepositoryUtils.generateDepartment(establishment);
        mainSection1 = generateEntitiesRepositoryUtils.generateMainsection(department);
        mainSection2 = generateEntitiesRepositoryUtils.generateMainsection(department);
        mainSection3 = generateEntitiesRepositoryUtils.generateMainsection(department);
    }

    @BeforeEach
    void setUp() {
        startMainSection();
    }

    @Transactional
    @Test
    void shouldSetTheFirstMainSectionWithTheInternalCodeEqualToOne() {
        assertEquals(BigInteger.valueOf(1), mainSection1.getCode());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        assertEquals(BigInteger.valueOf(3), mainSection3.getCode());
    }
}