package br.com.amorim.supermarket.testutils.generateentitiesunittests.person;

import br.com.amorim.supermarket.common.enums.ScholarityType;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.testutils.generatecnpj.GenerateCPF;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata.UserDataTest;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class PersonTest {

    public Person generatePerson () {
        Random firstNameRandom = new Random();
        Random middleNameRandom = new Random();
        Random lastNameRandom = new Random();
        Random rgRandom = new Random();
        Random emailRandom = new Random();
        UserDataTest userDataTest = new UserDataTest();
        GenerateCPF generateCPF = new GenerateCPF();
        Person person = new Person();

        var firstName = firstNameRandom.nextInt(100000, 199999);
        var middleName = middleNameRandom.nextInt(100000, 199999);
        var lastName = lastNameRandom.nextInt(100000, 199999);
        var rg = rgRandom.nextInt(100000, 199999);
        var email = emailRandom.nextInt(100000, 199999);

        person.setId(UUID.randomUUID());
        person.setFirstName(String.valueOf(firstName));
        person.setMiddleName(String.valueOf(middleName));
        person.setLastName(String.valueOf(lastName));
        person.setCpf(generateCPF.cpf(false));
        person.setRg(String.valueOf(rg));
        person.setDependents(false);
        person.setEmail(String.valueOf(email));
        person.setBirthDate(LocalDate.of(1987, 01, 01));
        person.setFatherName("Teste Father");
        person.setMotherName("Teste Mother");
        person.setNaturalness("São José");
        person.setNationality("Brasileiro");
        person.setScholarity(ScholarityType.ELEMENTARY_SCHOOL);
        person.setUserData(userDataTest.generateUserData());
        return person;
    }
}
