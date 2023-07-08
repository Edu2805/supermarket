package br.com.amorim.supermarket.repository.employee;

import br.com.amorim.supermarket.model.employee.Employee;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.model.subsection.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByPerson(Person person);
    boolean existsBySubSection(SubSection subSection);
}
