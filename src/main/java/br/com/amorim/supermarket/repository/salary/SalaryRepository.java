package br.com.amorim.supermarket.repository.salary;

import br.com.amorim.supermarket.model.salary.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, UUID> {

    Optional<Salary> findByPositionAndSalaryRange(String position, String salaryRange);
    Optional<Salary> findByPosition(String position);
}
