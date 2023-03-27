package br.com.amorim.supermarket.controller.otherdiscount;

import br.com.amorim.supermarket.controller.otherdiscount.dto.ConverterOtherDiscountMapper;
import br.com.amorim.supermarket.controller.otherdiscount.dto.OtherDiscountDTO;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.service.otherdiscount.OtherDiscountCrudService;
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
@RequestMapping("api/otherdiscount")
@Api("Other Discount")
public class OtherDiscountController {

    private OtherDiscountCrudService otherDiscountCrudService;
    private ConverterOtherDiscountMapper converterOtherDiscount;

    @GetMapping
    @ApiOperation("Get all other discounts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Other discounts returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the other discounts")
    })
    public Page<OtherDiscount> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Other discounts list page") int page,
                                        @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return otherDiscountCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific other discount")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Other discount returned successfully"),
            @ApiResponse(code = 404, message = "Other discount not found for given id")
    })
    public OtherDiscount getById (@PathVariable @ApiParam("Other discount id") UUID id) {
        return otherDiscountCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a other discount")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Other discount successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the other discount")
    })
    public OtherDiscount save (@RequestBody @Valid @ApiParam("Parameters for saving the other discount")
                                   OtherDiscountDTO otherDiscountDTO) {
        var newOtherDiscount = converterOtherDiscount.createOrUpdateOtherDiscountMapper(otherDiscountDTO);
        return otherDiscountCrudService.save(newOtherDiscount);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a other discount")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Other discount successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the other discount")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the other discount")
                            OtherDiscountDTO otherDiscountDTO, @PathVariable @ApiParam("Other discount id") UUID id) {
        var newOtherDiscount = converterOtherDiscount.createOrUpdateOtherDiscountMapper(otherDiscountDTO);
        otherDiscountCrudService.update(newOtherDiscount, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific other discount")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Other discount deleted successfully"),
            @ApiResponse(code = 404, message = "Other discount not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Other discount id") UUID id) {
        otherDiscountCrudService.delete(id);
    }
}
