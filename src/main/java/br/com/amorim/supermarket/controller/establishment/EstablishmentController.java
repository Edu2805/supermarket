package br.com.amorim.supermarket.controller.establishment;

import br.com.amorim.supermarket.controller.establishment.dto.ConvertEstablishmentMapper;
import br.com.amorim.supermarket.controller.establishment.dto.EstablishmentDTO;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.service.establishment.EstablishmentCrudServiceImpl;
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
@RequestMapping("api/establishment")
@Api("Establishment")
public class EstablishmentController {

    private EstablishmentCrudServiceImpl establishmentService;
    private ConvertEstablishmentMapper convertEstablishmentMapper;

    @GetMapping
    @ApiOperation("Get all establishments")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Establishments returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the establishments")
    })
    public Page<Establishment> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Establishment list page") int page,
                                        @RequestParam(
                                                value = "size",
                                                required = false,
                                                defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return establishmentService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific establishment")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Establishment returned successfully"),
            @ApiResponse(code = 404, message = "Establishment not found for given id")
    })
    public Establishment getById (@PathVariable @ApiParam("Establishment id") UUID id) {
        return establishmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a establishment")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Establishment successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the establishment")
    })
    public Establishment save (@RequestBody @Valid @ApiParam("Parameters for saving the establishment") EstablishmentDTO establishmentDTO) {
        var newEstablishment = convertEstablishmentMapper
                .createOrUpdateEstablishmentMapper(establishmentDTO);
        return establishmentService.save(newEstablishment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a establishment")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Establishment successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the establishment")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the establishment")
                            EstablishmentDTO establishmentDTO, @PathVariable @ApiParam("Establishment id") UUID id) {
        var newEstablishment = convertEstablishmentMapper
                .createOrUpdateEstablishmentMapper(establishmentDTO);
        establishmentService.update(newEstablishment, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific establishment")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Establishment deleted successfully"),
            @ApiResponse(code = 404, message = "Establishment not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Establishment id") UUID id) {
        establishmentService.delete(id);
    }
}
