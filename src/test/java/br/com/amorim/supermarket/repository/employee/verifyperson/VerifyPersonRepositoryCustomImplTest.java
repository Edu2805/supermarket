package br.com.amorim.supermarket.repository.employee.verifyperson;

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
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
class VerifyPersonRepositoryCustomImplTest {

    @Autowired
    private VerifyPersonRepositoryCustom verifyPersonRepositoryCustom;
    @Autowired
    private GenerateEntitiesRepositoryUtils generateEntitiesRepository;

    private Employee employee1;
    private Person person1;
    private SubSection subSection;
    private JobPosition jobPosition;
    private UserData userData1;
    private MainSection mainSection;
    private Department department;
    private Establishment establishment;
    private OtherAddition otherAddition;
    private OtherDiscount otherDiscount;
    private Salary salary;


    private void startEmployee() {
        establishment = generateEntitiesRepository.generateEstablishment();
        department = generateEntitiesRepository.generateDepartment(establishment);
        userData1 = generateEntitiesRepository.generateUserData();
        mainSection = generateEntitiesRepository.generateMainsection(department);
        person1 = generateEntitiesRepository.generatePerson(userData1);
        subSection = generateEntitiesRepository.generateSubsection(mainSection);
        otherAddition = generateEntitiesRepository.generateOtherAddition();
        otherDiscount = generateEntitiesRepository.generateOtherDiscount();
        salary = generateEntitiesRepository.generateSalary(otherAddition, otherDiscount);
        jobPosition = generateEntitiesRepository.generateJobPosition(salary);
        employee1 = generateEntitiesRepository.generateEmployee(person1, subSection, jobPosition);
    }

    @BeforeEach
    void setUp() {
        startEmployee();
    }

    @Transactional
    @Test
    void shouldReturnFalseWhenNotExistsByEmployeePerson() {
        EmployeeTest employeeTest = new EmployeeTest();

        var existsPerson = verifyPersonRepositoryCustom
                .existsByEmployeePerson(employeeTest.generateEmployee());

        assertFalse(existsPerson);
    }

    @Transactional
    @Test
    void shouldReturnTrueWhenAlreadyExistsByEmployeePerson() {
        var existsPerson = verifyPersonRepositoryCustom
                .existsByEmployeePerson(employee1);

        assertTrue(existsPerson);
    }
}