package br.com.amorim.supermarket.repository.person.verifycpfrepositorycustom;

import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCPF;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class VerifyCpfRepositoryCustomImplTest {

    @Autowired
    private VerifyCpfRepositoryCustomImpl verifyCpfRepositoryCustom;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepositoryUtils;
    private Person person1;
    private Person person2;
    private UserData userData1;
    private UserData userData2;

    private void startPerson () {
        person1 = new Person();
        person2 = new Person();
        userData1 = new UserData();
        userData2 = new UserData();

        userData1 = generateEntitiesRepositoryUtils.generateUserData();
        userData2 = generateEntitiesRepositoryUtils.generateUserData();
        person1 = generateEntitiesRepositoryUtils.generatePerson(userData1);
        person2 = generateEntitiesRepositoryUtils.generatePerson(userData2);
    }

    @BeforeEach
    void setUp() {
        startPerson();
    }

    @Test
    void shouldReturnTrueWhenCpfAlreadyExistsInDatabase() {
        var verifyCpf = verifyCpfRepositoryCustom
                .isCpfAlreadyExistsInTheDatabase(person1);
        assertTrue(verifyCpf);
    }

    @Test
    void shouldReturnFalseWhenCpfNotExistsInDatabase() {
        GenerateCPF generateCPF = new GenerateCPF();
        person2.setCpf(generateCPF.cpf(false));
        var verifyCpf = verifyCpfRepositoryCustom
                .isCpfAlreadyExistsInTheDatabase(person2);
        assertFalse(verifyCpf);
    }
}