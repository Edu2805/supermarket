package br.com.amorim.supermarket.controller.establishment;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.service.establishment.EstablishmentService;
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
@RequestMapping("establishment")
public class EstablishmentController {

    private EstablishmentService establishmentService;

    @GetMapping
    public List<Establishment> findAll () {
        return establishmentService.getAll();
    }

    @GetMapping("/{id}")
    public Establishment getById (@PathVariable UUID id) {
        return establishmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Establishment save (@RequestBody @Valid Establishment establishment) {
        return establishmentService.save(establishment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid Establishment establishment, @PathVariable UUID id) {
        establishmentService.update(establishment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        establishmentService.delete(id);
    }
}
