package br.com.amorim.supermarket.repository.employee.verifyperson;

import br.com.amorim.supermarket.SupermarketApplication;
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
import br.com.amorim.supermarket.repository.department.DepartmentRepository;
import br.com.amorim.supermarket.repository.employee.EmployeeRepository;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.jobposition.JobPositionRepository;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.testutils.generateentitiesrepositorytest.GenerateEntitiesRepositoryUtils;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.employee.EmployeeTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource("classpath:application.properties")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class VerifyPersonRepositoryCustomImplTest {

    @Autowired
    private VerifyPersonRepositoryCustom verifyPersonRepositoryCustom;
    @Autowired
    private EstablishmentRepository establishmentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserDataRepository userDataRepository;
    @Autowired
    private MainSectionRepository mainSectionRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SubSectionRepository subSectionRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private JobPositionRepository jobPositionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EntityManager entityManager;
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

    private void deleteEmployee() {
        establishmentRepository.delete(establishment);
        departmentRepository.delete(department);
        userDataRepository.delete(userData1);
        mainSectionRepository.delete(mainSection);
        personRepository.delete(person1);
        subSectionRepository.delete(subSection);
        salaryRepository.delete(salary);
        jobPositionRepository.delete(jobPosition);
        employeeRepository.delete(employee1);
    }

    @BeforeEach
    void setUp() {
        startEmployee();
    }

    @AfterEach
    void cleanUp() {
        deleteEmployee();
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