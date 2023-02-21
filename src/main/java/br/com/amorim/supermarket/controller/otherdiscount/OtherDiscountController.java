package br.com.amorim.supermarket.controller.otherdiscount;

import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.service.otherdiscount.OtherDiscountCrudService;
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
@RequestMapping("otherdiscount")
public class OtherDiscountController {

    private OtherDiscountCrudService otherDiscountCrudService;

    @GetMapping
    public Page<OtherDiscount> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                        @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") int size) {
        return otherDiscountCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public OtherDiscount getById (@PathVariable UUID id) {
        return otherDiscountCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public OtherDiscount save (@RequestBody @Valid OtherDiscount otherDiscount) {
        return otherDiscountCrudService.save(otherDiscount);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid OtherDiscount otherDiscount, @PathVariable UUID id) {
        otherDiscountCrudService.update(otherDiscount, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        otherDiscountCrudService.delete(id);
    }
}
