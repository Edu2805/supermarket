package br.com.amorim.supermarket.controller.mainsection;

import br.com.amorim.supermarket.controller.mainsection.dto.ConvertMainSectionMapper;
import br.com.amorim.supermarket.controller.mainsection.dto.MainSectionDTO;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.service.mainsection.MainSectionCrudServiceImpl;
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
@RequestMapping("api/mainsection")
public class MainSectionController {

    private MainSectionCrudServiceImpl mainSectionService;
    private ConvertMainSectionMapper convertMainSectionMapper;

    @GetMapping
    public Page<MainSection> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                        @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") int size) {
        return mainSectionService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public MainSection getById (@PathVariable UUID id) {
        return mainSectionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public MainSection save (@RequestBody @Valid MainSectionDTO mainSectionDTO) {
        var newMainSection = convertMainSectionMapper.createMainSectionMapper(mainSectionDTO);
        return mainSectionService.save(newMainSection);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid MainSectionDTO mainSectionDTO, @PathVariable UUID id) {
        var updateMainSection = convertMainSectionMapper.createMainSectionMapper(mainSectionDTO);
        mainSectionService.update(updateMainSection, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        mainSectionService.delete(id);
    }
}
