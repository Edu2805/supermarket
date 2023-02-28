package br.com.amorim.supermarket.controller.jobposition;

import br.com.amorim.supermarket.controller.jobposition.dto.ConvertJobPositionMapper;
import br.com.amorim.supermarket.controller.jobposition.dto.JobPositionDTO;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.service.jobposition.JobPositionCrudServiceImpl;
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
public class JobPositionController {

    private JobPositionCrudServiceImpl jobPositionService;
    private ConvertJobPositionMapper convertJobPositionMapper;

    @GetMapping
    public Page<JobPosition> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                        @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") int size) {
        return jobPositionService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public JobPosition getById (@PathVariable UUID id) {
        return jobPositionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public JobPosition save (@RequestBody @Valid JobPositionDTO jobPositionDTO) {
        var newJobPosition = convertJobPositionMapper.createOrUpdateJobPositionMapper(jobPositionDTO);
        return jobPositionService.save(newJobPosition);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid JobPositionDTO jobPositionDTO, @PathVariable UUID id) {
        var updateJobPosition = convertJobPositionMapper.createOrUpdateJobPositionMapper(jobPositionDTO);
        jobPositionService.update(updateJobPosition, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        jobPositionService.delete(id);
    }
}
