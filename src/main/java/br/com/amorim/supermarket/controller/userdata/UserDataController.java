package br.com.amorim.supermarket.controller.userdata;

import br.com.amorim.supermarket.common.exception.invalidpasswordexception.InvalidPasswordException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.controller.common.accessrestriction.AccessRestriction;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.ConverterUserDataRequestMapper;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.CredentialsDTO;
import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.ConverterUserDataResponseMapper;
import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.TokenDTO;
import br.com.amorim.supermarket.controller.userdata.dto.requestconfigurationdto.UserDataDTO;
import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.UserDataResponseDTO;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.service.jwt.JwtService;
import br.com.amorim.supermarket.service.userdata.UserDataCrudServiceImpl;
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

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor

@RestController
@RequestMapping("api/user")
public class UserDataController {

    private UserDataCrudServiceImpl userDataService;
    private JwtService jwtService;
    private ConverterUserDataRequestMapper converterUserDataMapper;
    private AccessRestriction accessRestriction;
    private ConverterUserDataResponseMapper converterUserDataResponseMapper;

    @GetMapping
    public Page<UserData> findAll (@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                   @RequestParam(
                                           value = "size",
                                           required = false,
                                           defaultValue = "20") int size) {
        return userDataService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public UserDataResponseDTO getById (@PathVariable UUID id) {
        if(accessRestriction.isAuthorized(id)) {
            var findUser = userDataService.findById(id);
            return converterUserDataResponseMapper.getUserDataMapper(findUser);
        }
        throw new ResponseStatusException(UNAUTHORIZED);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserData save (@RequestBody @Valid UserDataDTO userDataDTO) {
        var newUserData = converterUserDataMapper
                .createOrUpdateUserDataMapper(userDataDTO);
        return userDataService.save(newUserData);
    }

    @PostMapping("auth")
    public TokenDTO authenticate(@RequestBody @Valid CredentialsDTO credentialsDTO) {
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
    public void update (@RequestBody @Valid UserDataDTO userDataDTO, @PathVariable UUID id) {
        var newUserData = converterUserDataMapper
                .createOrUpdateUserDataMapper(userDataDTO);
        userDataService.update(newUserData, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable UUID id) {
        userDataService.delete(id);
    }
}
