package br.com.amorim.supermarket.repository.department.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigInteger;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class GenerateInternalCodeDepartmentRepositoryCustomImplTest {

    @Autowired
    private GenerateInternalCodeDepartmentRepositoryCustomImpl generateInternalCodeDepartmentRepositoryCustom;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntities;

    private Department department1;
    private Department department2;
    private Department department3;
    private Establishment establishment;

    private void estartDepartment() {
        establishment = generateEntities.generateEstablishment();
        department1 = generateEntities.generateDepartment(establishment);
        department2 = generateEntities.generateDepartment(establishment);
        department3 = generateEntities.generateDepartment(establishment);
    }

    private void deleteDepartment() {
        departmentRepository.delete(department1);
        departmentRepository.delete(department2);
        departmentRepository.delete(department3);
        establishmentRepository.delete(establishment);
    }

    @BeforeEach
    void setUp() {
        estartDepartment();
    }

    @AfterEach
    void cleanUp() {
        deleteDepartment();
    }

    @Transactional
    @Test
    void shouldSetTheFirstDepartmentWithTheInternalCodeEqualToOne() {
        Assertions.assertEquals(BigInteger.valueOf(1), department1.getCode());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        Assertions.assertEquals(BigInteger.valueOf(3), department3.getCode());
    }
}