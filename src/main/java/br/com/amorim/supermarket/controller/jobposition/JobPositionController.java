package br.com.amorim.supermarket.controller.jobposition;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.service.jobposition.JobPositionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor

@RestController
@RequestMapping("jobposition")
public class JobPositionController {

    private JobPositionService jobPositionService;

    @GetMapping
    public List<JobPosition> findAll () {
        return jobPositionService.getAll();
    }

    @GetMapping("/{id}")
    public JobPosition getById (@PathVariable UUID id) {
        return jobPositionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public JobPosition save (@RequestBody @Valid JobPosition jobPosition) {
        return jobPositionService.save(jobPosition);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid JobPosition jobPosition, @PathVariable UUID id) {
        jobPositionService.update(jobPosition, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        jobPositionService.delete(id);
    }
}
