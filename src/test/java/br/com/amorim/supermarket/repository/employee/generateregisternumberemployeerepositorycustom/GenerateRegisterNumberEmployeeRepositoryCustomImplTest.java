package br.com.amorim.supermarket.repository.employee.generateregisternumberemployeerepositorycustom;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.model.userdata.UserData;
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
class GenerateRegisterNumberEmployeeRepositoryCustomImplTest {

    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Person person1;
    private Person person2;
    private Person person3;
    private SubSection subSection;
    private JobPosition jobPosition;
    private UserData userData1;
    private UserData userData2;
    private UserData userData3;
    private MainSection mainSection;
    private Department department;
    private Establishment establishment;
    private Salary salary;
    private OtherAddition otherAddition;
    private OtherDiscount otherDiscount;


    private void startEmployee() {
        establishment = generateEntitiesRepository.generateEstablishment();
        department = generateEntitiesRepository.generateDepartment(establishment);
        userData1 = generateEntitiesRepository.generateUserData();
        userData2 = generateEntitiesRepository.generateUserData();
        userData3 = generateEntitiesRepository.generateUserData();
        mainSection = generateEntitiesRepository.generateMainsection(department);
        person1 = generateEntitiesRepository.generatePerson(userData1);
        person2 = generateEntitiesRepository.generatePerson(userData2);
        person3 = generateEntitiesRepository.generatePerson(userData3);
        subSection = generateEntitiesRepository.generateSubsection(mainSection);
        otherAddition = generateEntitiesRepository.generateOtherAddition();
        otherDiscount = generateEntitiesRepository.generateOtherDiscount();
        salary = generateEntitiesRepository.generateSalary(otherAddition, otherDiscount);
        jobPosition = generateEntitiesRepository.generateJobPosition(salary);
        employee1 = generateEntitiesRepository.generateEmployee(person1, subSection, jobPosition);
        employee2 = generateEntitiesRepository.generateEmployee(person2, subSection, jobPosition);
        employee3 = generateEntitiesRepository.generateEmployee(person3, subSection, jobPosition);
    }

    @BeforeEach
    void setUp() {
        startEmployee();
    }

    @Transactional
    @Test
    void shouldSetTheFirstEmployeeWithTheInternalCodeEqualToOne() {
        assertEquals(BigInteger.valueOf(1), employee1.getRegisterNumber());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        assertEquals(BigInteger.valueOf(3), employee3.getRegisterNumber());
    }
}