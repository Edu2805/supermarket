package br.com.amorim.supermarket.controller.person;

import br.com.amorim.supermarket.controller.person.dto.ConverterPersonMapperImpl;
import br.com.amorim.supermarket.controller.person.dto.PersonDTO;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.service.person.PersonCrudServiceImpl;
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
@RequestMapping("api/person")
@Api("Person")
public class PersonController {

    private PersonCrudServiceImpl personService;
    private ConverterPersonMapperImpl converterPersonMapper;

    @GetMapping
    @ApiOperation("Get all people")
    @ApiResponses({
            @ApiResponse(code = 200, message = "People returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the people")
    })
    public Page<Person> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("People list page") int page,
                                 @RequestParam(
                                         value = "size",
                                         required = false,
                                         defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return personService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Person returned successfully"),
            @ApiResponse(code = 404, message = "Person not found for given id")
    })
    public Person getById (@PathVariable @ApiParam("Person id") UUID id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a person")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Person successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the person")
    })
    public Person save (@RequestBody @Valid @ApiParam("Parameters for saving the person") PersonDTO personDTO) {
        var newPerson = converterPersonMapper.createOrUpdatePersonMapper(personDTO);
        return personService.save(newPerson);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a person")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Person successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the person")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the person")
                            PersonDTO personDTO, @PathVariable @ApiParam("Person id") UUID id) {
        var newPerson = converterPersonMapper.createOrUpdatePersonMapper(personDTO);
        personService.update(newPerson, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific person")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Person deleted successfully"),
            @ApiResponse(code = 404, message = "Person not found for given id")
    })
    public void delete (@PathVariable @ApiParam("Person id") UUID id) {
        personService.delete(id);
    }
}
