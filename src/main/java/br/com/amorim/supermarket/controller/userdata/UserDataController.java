package br.com.amorim.supermarket.controller.userdata;

import br.com.amorim.supermarket.common.exception.invalidpasswordexception.InvalidPasswordException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.controller.common.accessrestriction.AccessRestriction;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.ConverterUserDataRequestMapper;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTO;
import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.ConverterUserDataResponseMapper;
import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.RegisterUserResponseOutput;
import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.TokenDTO;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.UserDataDTO;
import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.UserDataResponseDTO;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.service.jwt.JwtService;
import br.com.amorim.supermarket.service.userdata.UserDataCrudServiceImpl;
import br.com.amorim.supermarket.service.userdata.userdetail.UserLoginServiceImpl;
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
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@AllArgsConstructor

@RestController
@RequestMapping("api/user")
@Api("User Data")
public class UserDataController {

    private UserDataCrudServiceImpl userDataService;
    private UserLoginServiceImpl userDetailsService;
    private JwtService jwtService;
    private ConverterUserDataRequestMapper converterUserDataMapper;
    private AccessRestriction accessRestriction;
    private ConverterUserDataResponseMapper converterUserDataResponseMapper;

    @GetMapping
    @ApiOperation("Get all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users returned successfully"),
            @ApiResponse(code = 404, message = "An error occurred while fetching the users")
    })
    public Page<UserData> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") @ApiParam("Users list page") int page,
                                   @RequestParam(
                                           value = "size",
                                           required = false,
                                           defaultValue = "20") @ApiParam("Number of records on each page") int size) {
        return userDataService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a specific user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User returned successfully"),
            @ApiResponse(code = 404, message = "User not found for given id")
    })
    public UserDataResponseDTO getById (@PathVariable @ApiParam("User id") UUID id) {
        if(accessRestriction.isAuthorized(id)) {
            var findUser = userDataService.findById(id);
            return converterUserDataResponseMapper.getUserDataMapper(findUser);
        }
        throw new ResponseStatusException(UNAUTHORIZED);
    }

    @GetMapping("/hr/{id}")
    @ApiOperation("Get a specific user for role rh")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User returned successfully"),
            @ApiResponse(code = 404, message = "User not found for given id")
    })
    public UserData getUser (@PathVariable @ApiParam("User id") UUID id) {
        return userDataService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Save a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User successfully saved"),
            @ApiResponse(code = 400, message = "An error occurred while saving the user")
    })
    public RegisterUserResponseOutput save (@RequestBody @Valid @ApiParam("Parameters for saving the user") UserDataDTO userDataDTO) {
        var newUserData = converterUserDataMapper
                .createOrUpdateUserDataMapper(userDataDTO);
        var save = userDataService.save(newUserData);
        return new RegisterUserResponseOutput(save.getUserName());
    }

    @PostMapping("auth")
    @ApiOperation("Authenticate a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User successfully autenticated"),
            @ApiResponse(code = 400, message = "An error occurred while autenticate the user")
    })
    public TokenDTO authenticate(@RequestBody @Valid @ApiParam("User Credentials") CredentialsDTO credentialsDTO) {
        userDetailsService.existsUserName(credentialsDTO.getLogin());
        try {
            UserData userData = UserData.builder()
                    .userName(credentialsDTO.getLogin())
                    .password(credentialsDTO.getPassword())
                    .build();
            var token = jwtService.generateToken(userData);
            return new TokenDTO(userData.getUserName(), token);
        } catch (NotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Update a user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User successfully updated"),
            @ApiResponse(code = 400, message = "An error occurred while updating the user")
    })
    public void update (@RequestBody @Valid @ApiParam("Parameters for updating the user")
                            UserDataDTO userDataDTO, @PathVariable @ApiParam("User id") UUID id) {
        var newUserData = converterUserDataMapper
                .createOrUpdateUserDataMapper(userDataDTO);
        userDataService.update(newUserData, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Delete a specific user")
    @ApiResponses({
            @ApiResponse(code = 204, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found for given id")
    })
    public void delete (@PathVariable @ApiParam("User id") UUID id) {
        userDataService.delete(id);
    }
}
