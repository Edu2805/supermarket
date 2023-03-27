package br.com.amorim.supermarket.controller.subsection;

import br.com.amorim.supermarket.controller.subsection.dto.ConvertSubSectionMapper;
import br.com.amorim.supermarket.controller.subsection.dto.SubSectionDTO;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.service.subsection.SubSectionCrudServiceImpl;
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
@RequestMapping("api/subsection")
@Api("Sub Section")
public class SubSectionController {

    private SubSectionCrudServiceImpl subSectionService;
    private ConvertSubSectionMapper convertSubSectionMapper;

    @GetMapping
    @ApiOperation("Get all sub sections")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Sub sections returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the sub sections")
    })
    public Page<SubSection> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Sub sections list page") int page,
                                     @RequestParam(
                                             value = "size",
                                             required = false,
                                             defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return subSectionService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific salary")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Salary returned successfully"),
            @ApiResponse(code = 404, message = "Salary not found for given id")
    })
    public SubSection getById (@PathVariable @ApiParam("Salary id") UUID id) {
        return subSectionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a salary")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Salary successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the salary")
    })
    public SubSection save (@RequestBody @Valid @ApiParam("Parameters for saving the salary") SubSectionDTO subSectionDTO) {
        var newSubSection = convertSubSectionMapper.createSubSectionMapper(subSectionDTO);
        return subSectionService.save(newSubSection);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a salary")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Salary successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the salary")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the salary")
                            SubSectionDTO subSectionDTO, @PathVariable @ApiParam("Salary id") UUID id) {
        var newSubSection = convertSubSectionMapper.createSubSectionMapper(subSectionDTO);
        subSectionService.update(newSubSection, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific salary")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Salary deleted successfully"),
            @ApiResponse(code = 404, message = "Salary not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Salary id") UUID id) {
        subSectionService.delete(id);
    }
}
