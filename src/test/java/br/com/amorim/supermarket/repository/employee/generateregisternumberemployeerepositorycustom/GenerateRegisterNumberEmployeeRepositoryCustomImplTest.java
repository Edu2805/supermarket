package br.com.amorim.supermarket.repository.employee.generateregisternumberemployeerepositorycustom;

import br.com.amorim.supermarket.SupermarketApplication;
import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.model.mainsection.MainSection;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SupermarketApplication.class)
class GenerateRegisterNumberEmployeeRepositoryCustomImplTest {

    @Autowired
    private GenerateRegisterNumberEmployeeRepositoryCustomImpl generateRegisterNumberEmployeeRepositoryCustom;
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
        salary = generateEntitiesRepository.generateSalary();
        jobPosition = generateEntitiesRepository.generateJobPosition(salary);
        employee1 = generateEntitiesRepository.generateEmployee(person1, subSection, jobPosition);
        employee2 = generateEntitiesRepository.generateEmployee(person2, subSection, jobPosition);
        employee3 = generateEntitiesRepository.generateEmployee(person3, subSection, jobPosition);
    }

    private void deleteEmployee() {
        establishmentRepository.delete(establishment);
        departmentRepository.delete(department);
        userDataRepository.delete(userData1);
        userDataRepository.delete(userData2);
        userDataRepository.delete(userData3);
        mainSectionRepository.delete(mainSection);
        personRepository.delete(person1);
        personRepository.delete(person2);
        personRepository.delete(person3);
        subSectionRepository.delete(subSection);
        salaryRepository.delete(salary);
        jobPositionRepository.delete(jobPosition);
        employeeRepository.delete(employee1);
        employeeRepository.delete(employee2);
        employeeRepository.delete(employee3);
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
    void shouldSetTheFirstEmployeeWithTheInternalCodeEqualToOne() {
        assertEquals(BigInteger.valueOf(1), employee1.getRegisterNumber());
    }

    @Transactional
    @Test
    void shouldAddCodeSequenceBeforeSaving() {
        assertEquals(BigInteger.valueOf(3), employee3.getRegisterNumber());
    }
}