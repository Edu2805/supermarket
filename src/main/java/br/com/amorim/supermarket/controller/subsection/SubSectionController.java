package br.com.amorim.supermarket.controller.subsection;

import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.service.subsection.SubSectionService;
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
@RequestMapping("subsection")
public class SubSectionController {

    private SubSectionService subSectionService;

    @GetMapping
    public List<SubSection> findAll () {
        return subSectionService.getAll();
    }

    @GetMapping("/{id}")
    public SubSection getById (@PathVariable UUID id) {
        return subSectionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SubSection save (@RequestBody @Valid SubSection subSection) {
        return subSectionService.save(subSection);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid SubSection subSection, @PathVariable UUID id) {
        subSectionService.update(subSection, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        subSectionService.delete(id);
    }
}
