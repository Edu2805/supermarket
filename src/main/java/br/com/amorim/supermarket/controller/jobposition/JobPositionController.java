package br.com.amorim.supermarket.controller.jobposition;

import br.com.amorim.supermarket.controller.jobposition.dto.ConvertJobPositionMapper;
import br.com.amorim.supermarket.controller.jobposition.dto.JobPositionDTO;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.service.jobposition.JobPositionCrudServiceImpl;
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
@RequestMapping("api/jobposition")
@Api("Job position")
public class JobPositionController {

    private JobPositionCrudServiceImpl jobPositionService;
    private ConvertJobPositionMapper convertJobPositionMapper;

    @GetMapping
    @ApiOperation("Get all job positions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Job positions returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the job positions")
    })
    public Page<JobPosition> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Job positions list page") int page,
                                      @RequestParam(
                                              value = "size",
                                              required = false,
                                              defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return jobPositionService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific job position")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Job position returned successfully"),
            @ApiResponse(code = 404, message = "Job position not found for given id")
    })
    public JobPosition getById (@PathVariable @ApiParam("Job position id") UUID id) {
        return jobPositionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a job position")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Job position successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the job position")
    })
    public JobPosition save (@RequestBody @Valid @ApiParam("Parameters for saving the job position") JobPositionDTO jobPositionDTO) {
        var newJobPosition = convertJobPositionMapper.createOrUpdateJobPositionMapper(jobPositionDTO);
        return jobPositionService.save(newJobPosition);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a job position")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Job position successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the job position")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the job position")
                            JobPositionDTO jobPositionDTO, @PathVariable @ApiParam("Job position id") UUID id) {
        var updateJobPosition = convertJobPositionMapper.createOrUpdateJobPositionMapper(jobPositionDTO);
        jobPositionService.update(updateJobPosition, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific job position")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Job position deleted successfully"),
            @ApiResponse(code = 404, message = "Job position not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Job position id") UUID id) {
        jobPositionService.delete(id);
    }
}
