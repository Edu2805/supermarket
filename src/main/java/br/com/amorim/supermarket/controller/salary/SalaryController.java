package br.com.amorim.supermarket.controller.salary;

import br.com.amorim.supermarket.controller.salary.dto.ConverterSalaryMapper;
import br.com.amorim.supermarket.controller.salary.dto.SalaryDTO;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.SalaryCrudServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor

@RestController
@RequestMapping("api/salary")
public class SalaryController {

    private SalaryCrudServiceImpl salaryService;
    private ConverterSalaryMapper converterSalaryMapper;

    @GetMapping
    public Page<Salary> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                          @RequestParam(
                                                  value = "size",
                                                  required = false,
                                                  defaultValue = "20") int size) {
        return salaryService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Salary getById (@PathVariable UUID id) {
        return salaryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Salary save (@RequestBody @Valid SalaryDTO salaryDTO) {
        var newSalary = converterSalaryMapper.createOrUpdateSalaryMapper(salaryDTO);
        return salaryService.save(newSalary);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid SalaryDTO salaryDTO, @PathVariable UUID id) {
        var newSalary = converterSalaryMapper.createOrUpdateSalaryMapper(salaryDTO);
        salaryService.update(newSalary, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        salaryService.delete(id);
    }
}
