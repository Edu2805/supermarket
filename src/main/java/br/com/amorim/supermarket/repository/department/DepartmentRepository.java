package br.com.amorim.supermarket.repository.department;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.establishment.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {

    boolean existsByName(String name);
    boolean existsByEstablishment(Establishment establishment);
}
