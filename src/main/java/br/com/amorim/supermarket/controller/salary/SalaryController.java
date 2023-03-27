package br.com.amorim.supermarket.controller.salary;

import br.com.amorim.supermarket.controller.salary.dto.ConverterSalaryMapper;
import br.com.amorim.supermarket.controller.salary.dto.SalaryDTO;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.service.salary.SalaryCrudServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api("Salary")
public class SalaryController {

    private SalaryCrudServiceImpl salaryService;
    private ConverterSalaryMapper converterSalaryMapper;

    @GetMapping
    @ApiOperation("Get all salaries")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Salaries returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the salaries")
    })
    public Page<Salary> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Salaries list page") int page,
                                 @RequestParam(
                                         value = "size",
                                         required = false,
                                         defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return salaryService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific salary")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Salary returned successfully"),
            @ApiResponse(code = 404, message = "Salary not found for given id")
    })
    public Salary getById (@PathVariable @ApiParam("Salary id") UUID id) {
        return salaryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a salary")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Salary successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the salary")
    })
    public Salary save (@RequestBody @Valid @ApiParam("Parameters for saving the salary") SalaryDTO salaryDTO) {
        var newSalary = converterSalaryMapper.createOrUpdateSalaryMapper(salaryDTO);
        return salaryService.save(newSalary);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a salary")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Salary successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the salary")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the salary")
                            SalaryDTO salaryDTO, @PathVariable @ApiParam("Salary id") UUID id) {
        var newSalary = converterSalaryMapper.createOrUpdateSalaryMapper(salaryDTO);
        salaryService.update(newSalary, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific salary")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Salary deleted successfully"),
            @ApiResponse(code = 404, message = "Salary not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Salary id") UUID id) {
        salaryService.delete(id);
    }
}
