package br.com.amorim.supermarket.repository.person.verifycpfrepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.testutils.generatedocument.GenerateCPF;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyCpfRepositoryCustomImplTest {

    @Autowired
    private VerifyCpfRepositoryCustomImpl verifyCpfRepositoryCustom;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepositoryUtils;
    @Autowired
    private PersonRepository personRepository;
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

    private void deletePerson () {
        personRepository.delete(person1);
        personRepository.delete(person2);
    }

    @BeforeEach
    void setUp() {
        startPerson();
    }

    @AfterEach
    void cleanUp() {
        deletePerson();
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