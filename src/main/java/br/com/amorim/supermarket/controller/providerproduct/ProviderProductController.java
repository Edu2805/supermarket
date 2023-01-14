package br.com.amorim.supermarket.controller.providerproduct;

import br.com.amorim.supermarket.controller.providerproduct.dto.ConvertProviderMapper;
import br.com.amorim.supermarket.controller.providerproduct.dto.ProviderProductDTO;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.service.providerproduct.ProviderProductService;
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
@RequestMapping("provider")
public class ProviderProductController {

    private ProviderProductService providerProductService;
    private ConvertProviderMapper convertProviderMapper;

    @GetMapping
    public List<ProviderProduct> findAll () {
        return providerProductService.getAll();
    }

    @GetMapping("/{id}")
    public ProviderProduct getById (@PathVariable UUID id) {
        return providerProductService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProviderProduct save (@RequestBody @Valid ProviderProductDTO providerProductDTO) {
        var newProviderProduct = convertProviderMapper
                .createOrUpdateProviderProductMapper(providerProductDTO);
        return providerProductService.save(newProviderProduct);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid ProviderProductDTO providerProductDTO, @PathVariable UUID id) {
        var newProviderProduct = convertProviderMapper
                .createOrUpdateProviderProductMapper(providerProductDTO);
        providerProductService.update(newProviderProduct, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        providerProductService.delete(id);
    }
}
