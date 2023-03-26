package br.com.amorim.supermarket.controller.mainsection;

import br.com.amorim.supermarket.controller.mainsection.dto.ConvertMainSectionMapper;
import br.com.amorim.supermarket.controller.mainsection.dto.MainSectionDTO;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.service.mainsection.MainSectionCrudServiceImpl;
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
@RequestMapping("api/mainsection")
@Api("Main Section")
public class MainSectionController {

    private MainSectionCrudServiceImpl mainSectionService;
    private ConvertMainSectionMapper convertMainSectionMapper;

    @GetMapping
    @ApiOperation("Get all main sections")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Main sections returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the main sections")
    })
    public Page<MainSection> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Main sections list page") int page,
                                      @RequestParam(
                                              value = "size",
                                              required = false,
                                              defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return mainSectionService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific main section")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Main section returned successfully"),
            @ApiResponse(code = 404, message = "Main section not found for given id")
    })
    public MainSection getById (@PathVariable @ApiParam("Main section id") UUID id) {
        return mainSectionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a main section")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Main section successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the main section")
    })
    public MainSection save (@RequestBody @Valid @ApiParam("Parameters for saving the main section") MainSectionDTO mainSectionDTO) {
        var newMainSection = convertMainSectionMapper.createMainSectionMapper(mainSectionDTO);
        return mainSectionService.save(newMainSection);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a main section")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Main section successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the main section")
    })
    public void update (@RequestBody @Valid @ApiParam("Main section for updating the main section")
                            MainSectionDTO mainSectionDTO, @PathVariable @ApiParam("Main section id") UUID id) {
        var updateMainSection = convertMainSectionMapper.createMainSectionMapper(mainSectionDTO);
        mainSectionService.update(updateMainSection, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific main section")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Main section deleted successfully"),
            @ApiResponse(code = 404, message = "Main section not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Main section id") UUID id) {
        mainSectionService.delete(id);
    }
}
