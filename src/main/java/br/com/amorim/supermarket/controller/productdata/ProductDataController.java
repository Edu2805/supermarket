package br.com.amorim.supermarket.controller.productdata;

import br.com.amorim.supermarket.controller.productdata.dto.mapper.UnityForProductMapper;
import br.com.amorim.supermarket.controller.productdata.dto.request.ProductUnityStringDTO;
import br.com.amorim.supermarket.controller.productdata.dto.response.ConvertProductUnitTypeStringDTO;
import br.com.amorim.supermarket.controller.productdata.dto.response.ProductUnitTypeStringDTO;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.service.productdata.ProductDataCrudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("api/product")
@Api("Product Data")
public class ProductDataController {

    private ProductDataCrudService productDataService;
    private UnityForProductMapper unityForProductMapper;
    private ConvertProductUnitTypeStringDTO convertProductUnitTypeStringDTO;

    @GetMapping
    @ApiOperation("Get all products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Products returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the products")
    })
    public Page<ProductData> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Products list page") int page,
                                      @RequestParam(
                                              value = "size",
                                              required = false,
                                              defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return productDataService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product returned successfully"),
            @ApiResponse(code = 404, message = "Product not found for given id")
    })
    public ProductUnitTypeStringDTO getById (@PathVariable @ApiParam("Product id") UUID id) {
        var productData = productDataService.findById(id);
        return convertProductUnitTypeStringDTO.mapper(productData);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the product")
    })
    public ProductData save (@RequestBody @Valid @ApiParam("Parameters for saving the product") ProductUnityStringDTO productDTO) {
        var newProduct = unityForProductMapper.productMapper(productDTO);
        return productDataService.save(newProduct);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the product")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the product")
                        ProductUnityStringDTO productDTO, @PathVariable @ApiParam("Product id") UUID id) {
        var newProduct = unityForProductMapper.productMapper(productDTO);
        productDataService.update(newProduct, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific product")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Product deleted successfully"),
            @ApiResponse(code = 404, message = "Product not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Product id") UUID id) {
        productDataService.delete(id);
    }
}
