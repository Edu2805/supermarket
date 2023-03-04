package br.com.amorim.supermarket.repository.department.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class GenerateInternalCodeDepartmentRepositoryCustomImplTest {

    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntities;

    private Department department1;
    private Department department2;
    private Department department3;
    private Establishment establishment;

    private void startDepartment() {
        establishment = generateEntities.generateEstablishment();
        department1 = generateEntities.generateDepartment(establishment);
        department2 = generateEntities.generateDepartment(establishment);
        department3 = generateEntities.generateDepartment(establishment);
    }

    @BeforeEach
    void setUp() {
        startDepartment();
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