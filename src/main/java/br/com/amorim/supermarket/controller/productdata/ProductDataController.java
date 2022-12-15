package br.com.amorim.supermarket.controller.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.service.productdata.ProductDataService;
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
@RequestMapping("product")
public class ProductDataController {

    private ProductDataService productDataService;

    @GetMapping
    public List<ProductData> findAll () {
        return productDataService.getAll();
    }

    @GetMapping("/{id}")
    public ProductData getById (@PathVariable UUID id) {
        return productDataService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductData save (@RequestBody @Valid ProductData productData) {
        return productDataService.save(productData);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update (@RequestBody @Valid ProductData productData, @PathVariable UUID id) {
        productDataService.update(productData, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        productDataService.delete(id);
    }
}