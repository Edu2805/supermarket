package br.com.amorim.supermarket.service.salary;

import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class SalaryService {

    SalaryRepository salaryRepository;

    public List<Salary> getAll () {
        return salaryRepository.findAll();
    }

    public Salary findById (UUID id) {
        return salaryRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Salário não encontrado");
                });
    }

    @Transactional
    public Salary save (Salary salary) {
        return salaryRepository.save(salary);
    }

    @Transactional
    public void update (Salary salary, UUID id) {
        salaryRepository.findById(id)
                .map(existingSalary -> {
                    salary.setId(existingSalary.getId());
                    salaryRepository.save(salary);
                    return existingSalary;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Salário não encontrado"));
    }

    @Transactional
    public void delete (UUID id) {
        salaryRepository.findById(id)
                .map(existingSalary -> {
                    salaryRepository.delete(existingSalary);
                    return existingSalary;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Salário não encontrado"));
    }
}
