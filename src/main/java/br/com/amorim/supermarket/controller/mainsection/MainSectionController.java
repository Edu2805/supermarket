package br.com.amorim.supermarket.controller.mainsection;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.service.mainsection.MainSectionService;
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
@RequestMapping("mainsection")
public class MainSectionController {

    private MainSectionService mainSectionService;

    @GetMapping
    public List<MainSection> findAll () {
        return mainSectionService.getAll();
    }

    @GetMapping("/{id}")
    public MainSection getById (@PathVariable UUID id) {
        return mainSectionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public MainSection save (@RequestBody @Valid MainSection mainSection) {
        return mainSectionService.save(mainSection);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid MainSection mainSection, @PathVariable UUID id) {
        mainSectionService.update(mainSection, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        mainSectionService.delete(id);
    }
}
