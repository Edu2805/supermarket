package br.com.amorim.supermarket.controller.userdata;

import br.com.amorim.supermarket.controller.userdata.dto.ConverterUserDataMapper;
import br.com.amorim.supermarket.controller.userdata.dto.UserDataDTO;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.service.userdata.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor

@RestController
@RequestMapping("user")
public class UserDataController {

    private UserDataService userDataService;
    private ConverterUserDataMapper converterUserDataMapper;

    @GetMapping
    public List<UserData> findAll () {
        return userDataService.getAll();
    }

    @GetMapping("/{id}")
    public UserData getById (@PathVariable UUID id) {
        return userDataService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public UserData save (@RequestBody @Valid UserDataDTO userDataDTO) {
        var newUserData = converterUserDataMapper
                .createOrUpdateUserDataMapper(userDataDTO);
        return userDataService.save(newUserData);
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
