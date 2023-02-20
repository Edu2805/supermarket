package br.com.amorim.supermarket.service.salary;

import br.com.amorim.supermarket.model.salary.Salary;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface SalaryCrudService {

    Page<Salary> getAll(int page, int size);
    Salary findById(UUID id);
    Salary save (Salary salary);
    void update (Salary salary, UUID id);
    void delete (UUID id);
}
