package br.com.amorim.supermarket.controller.providerproduct;

import br.com.amorim.supermarket.controller.providerproduct.dto.request.ConvertProviderMapper;
import br.com.amorim.supermarket.controller.providerproduct.dto.request.ProviderProductDTO;
import br.com.amorim.supermarket.controller.providerproduct.dto.response.ConvertProviderSubscriptionTypeString;
import br.com.amorim.supermarket.controller.providerproduct.dto.response.ProviderProductSubscriptionTypeStringDTO;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.service.providerproduct.ProviderProductCrudServiceImpl;
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
@RequestMapping("api/provider")
@Api("Provider Product")
public class ProviderProductController {

    private ProviderProductCrudServiceImpl providerProductService;
    private ConvertProviderMapper convertProviderMapper;
    private ConvertProviderSubscriptionTypeString convertProviderSubscriptionTypeString;

    @GetMapping
    @ApiOperation("Get all provider products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Provider products returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the provider products")
    })
    public Page<ProviderProduct> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Provider products list page") int page,
                                          @RequestParam(
                                                  value = "size",
                                                  required = false,
                                                  defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return providerProductService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific provider product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Provider product returned successfully"),
            @ApiResponse(code = 404, message = "Provider product not found for given id")
    })
    public ProviderProductSubscriptionTypeStringDTO getById (@PathVariable @ApiParam("Provider product id") UUID id) {
        var providerProduct = providerProductService.findById(id);
        return convertProviderSubscriptionTypeString.mapper(providerProduct);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a provider product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Provider product successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the provider product")
    })
    public ProviderProduct save (@RequestBody @Valid @ApiParam("Parameters for saving the provider product")
                                     ProviderProductDTO providerProductDTO) {
        var newProviderProduct = convertProviderMapper
                .createOrUpdateProviderProductMapper(providerProductDTO);
        return providerProductService.save(newProviderProduct);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a provider product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Provider product successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the provider product")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the provider product")
                            ProviderProductDTO providerProductDTO, @PathVariable @ApiParam("Provider product id") UUID id) {
        var newProviderProduct = convertProviderMapper
                .createOrUpdateProviderProductMapper(providerProductDTO);
        providerProductService.update(newProviderProduct, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific provider product")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Provider product deleted successfully"),
            @ApiResponse(code = 404, message = "Provider product not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Provider product id") UUID id) {
        providerProductService.delete(id);
    }
}
