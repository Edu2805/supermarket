package br.com.amorim.supermarket.repository.mainsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
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
class GenerateInternalCodeMainSectionRepositoryCustomImplTest {

    @Autowired
    private GenerateInternalCodeMainSectionRepositoryCustomImpl generateInternalCodeMainSectionRepositoryCustom;
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
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

    private void deleteMainSection() {
        mainSectionRepository.delete(mainSection1);
        mainSectionRepository.delete(mainSection2);
        mainSectionRepository.delete(mainSection3);
        departmentRepository.delete(department);
        establishmentRepository.delete(establishment);
    }

    @BeforeEach
    void setUp() {
        startMainSection();
    }

    @AfterEach
    void cleanUp() {
        deleteMainSection();
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