package br.com.amorim.supermarket.controller.otheraddition;

import br.com.amorim.supermarket.controller.otheraddition.dto.ConverterOtherAdditionMapper;
import br.com.amorim.supermarket.controller.otheraddition.dto.OtherAdditionDTO;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.service.otheraddition.OtherAdditionCrudService;
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
@RequestMapping("otheraddition")
public class OtherAdditionController {

    private OtherAdditionCrudService otherAdditionCrudService;
    private ConverterOtherAdditionMapper converterOtherAdditionDTO;

    @GetMapping
    public Page<OtherAddition> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                        @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") int size) {
        return otherAdditionCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public OtherAddition getById (@PathVariable UUID id) {
        return otherAdditionCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OtherAddition save (@RequestBody @Valid OtherAdditionDTO otherAdditionDTO) {
        var newOtherAddition = converterOtherAdditionDTO.createOrUpdateOtherAdditionMapper(otherAdditionDTO);
        return otherAdditionCrudService.save(newOtherAddition);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid OtherAdditionDTO otherAdditionDTO, @PathVariable UUID id) {
        var newOtherAddition = converterOtherAdditionDTO.createOrUpdateOtherAdditionMapper(otherAdditionDTO);
        otherAdditionCrudService.update(newOtherAddition, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        otherAdditionCrudService.delete(id);
    }
}
