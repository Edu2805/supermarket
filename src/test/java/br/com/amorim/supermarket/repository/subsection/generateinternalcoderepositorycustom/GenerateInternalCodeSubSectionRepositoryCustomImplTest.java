package br.com.amorim.supermarket.repository.subsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.model.subsection.SubSection;
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
class GenerateInternalCodeSubSectionRepositoryCustomImplTest {

    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepositoryUtils;

    private SubSection subSection1;
    private SubSection subSection2;
    private SubSection subSection3;
    private MainSection mainSection;
    private Department department;
    private Establishment establishment;

    private void startSubSection() {
        establishment = generateEntitiesRepositoryUtils.generateEstablishment();
        department = generateEntitiesRepositoryUtils.generateDepartment(establishment);
        mainSection = generateEntitiesRepositoryUtils.generateMainsection(department);
        subSection1 = generateEntitiesRepositoryUtils.generateSubsection(mainSection);
        subSection2 = generateEntitiesRepositoryUtils.generateSubsection(mainSection);
        subSection3 = generateEntitiesRepositoryUtils.generateSubsection(mainSection);
    }

    @BeforeEach
    void setUp() {
        startSubSection();
    }

    @Transactional
    @Test
    void shouldSetTheFirstSubSectionWithTheInternalCodeEqualToOne() {
        assertEquals(BigInteger.valueOf(1), subSection1.getCode());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        assertEquals(BigInteger.valueOf(3), subSection3.getCode());
    }
}