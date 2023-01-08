package br.com.amorim.supermarket.controller.productdata;

import br.com.amorim.supermarket.controller.productdata.dto.ConvertProductMapper;
import br.com.amorim.supermarket.controller.productdata.dto.ProductDTO;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.service.productdata.ProductDataCrudService;
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
@RequestMapping("product")
public class ProductDataController {

    private ProductDataCrudService productDataService;
    private ConvertProductMapper convertProductMapper;

    @GetMapping
    public Page<ProductData> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                      @RequestParam(
                                              value = "size",
                                              required = false,
                                              defaultValue = "20") int size) {
        return productDataService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ProductData getById (@PathVariable UUID id) {
        return productDataService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductData save (@RequestBody @Valid ProductDTO productDTO) {
        var newProduct = convertProductMapper.createProductMapper(productDTO);
        return productDataService.save(newProduct);
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
