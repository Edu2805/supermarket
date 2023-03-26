package br.com.amorim.supermarket.controller.otheraddition;

import br.com.amorim.supermarket.controller.otheraddition.dto.ConverterOtherAdditionMapper;
import br.com.amorim.supermarket.controller.otheraddition.dto.OtherAdditionDTO;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.service.otheraddition.OtherAdditionCrudService;
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
@RequestMapping("api/otheraddition")
@Api("Other Addition")
public class OtherAdditionController {

    private OtherAdditionCrudService otherAdditionCrudService;
    private ConverterOtherAdditionMapper converterOtherAdditionDTO;

    @GetMapping
    @ApiOperation("Get all other addtions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Other addtions returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the other addtions")
    })
    public Page<OtherAddition> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Other addtions list page") int page,
                                        @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return otherAdditionCrudService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific other addtion")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Other addtion returned successfully"),
            @ApiResponse(code = 404, message = "Other addtion not found for given id")
    })
    public OtherAddition getById (@PathVariable @ApiParam("Other addtion id") UUID id) {
        return otherAdditionCrudService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a other addtion")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Other addtion successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the other addtion")
    })
    public OtherAddition save (@RequestBody @Valid @ApiParam("Parameters for saving the other addtion")
                                   OtherAdditionDTO otherAdditionDTO) {
        var newOtherAddition = converterOtherAdditionDTO.createOrUpdateOtherAdditionMapper(otherAdditionDTO);
        return otherAdditionCrudService.save(newOtherAddition);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a other addtion")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Other addtion successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the other addtion")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the department")
                            OtherAdditionDTO otherAdditionDTO, @PathVariable @ApiParam("Other addtion id") UUID id) {
        var newOtherAddition = converterOtherAdditionDTO.createOrUpdateOtherAdditionMapper(otherAdditionDTO);
        otherAdditionCrudService.update(newOtherAddition, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific other addtion")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Other addtion deleted successfully"),
            @ApiResponse(code = 404, message = "Other addtion not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Other addtion id") UUID id) {
        otherAdditionCrudService.delete(id);
    }
}
