package br.com.amorim.supermarket.controller.subsection;

import br.com.amorim.supermarket.controller.subsection.dto.ConvertSubSectionMapper;
import br.com.amorim.supermarket.controller.subsection.dto.SubSectionDTO;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.service.subsection.SubSectionCrudServiceImpl;
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
@RequestMapping("subsection")
public class SubSectionController {

    private SubSectionCrudServiceImpl subSectionService;
    private ConvertSubSectionMapper convertSubSectionMapper;

    @GetMapping
    public Page<SubSection> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                      @RequestParam(
                                              value = "size",
                                              required = false,
                                              defaultValue = "20") int size) {
        return subSectionService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public SubSection getById (@PathVariable UUID id) {
        return subSectionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SubSection save (@RequestBody @Valid SubSectionDTO subSectionDTO) {
        var newSubSection = convertSubSectionMapper.createSubSectionMapper(subSectionDTO);
        return subSectionService.save(newSubSection);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid SubSectionDTO subSectionDTO, @PathVariable UUID id) {
        var newSubSection = convertSubSectionMapper.createSubSectionMapper(subSectionDTO);
        subSectionService.update(newSubSection, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        subSectionService.delete(id);
    }
}
