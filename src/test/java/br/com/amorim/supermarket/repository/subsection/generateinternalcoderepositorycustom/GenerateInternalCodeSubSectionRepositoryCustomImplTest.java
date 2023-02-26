package br.com.amorim.supermarket.repository.subsection.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
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
class GenerateInternalCodeSubSectionRepositoryCustomImplTest {

    @Autowired
    private GenerateInternalCodeSubSectionRepositoryCustomImpl generateInternalCodeSubSectionRepositoryCustom;
    @Autowired
    private SubSectionRepository subSectionRepository;
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
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

    private void deleteSubSection() {
        subSectionRepository.delete(subSection1);
        subSectionRepository.delete(subSection2);
        subSectionRepository.delete(subSection3);
        mainSectionRepository.delete(mainSection);
        mainSectionRepository.delete(mainSection);
        mainSectionRepository.delete(mainSection);
        departmentRepository.delete(department);
        establishmentRepository.delete(establishment);
    }

    @BeforeEach
    void setUp() {
        startSubSection();
    }

    @AfterEach
    void cleanUp() {
        deleteSubSection();
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