package br.com.amorim.supermarket.service.userdata;

import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class UserDataService {

    UserDataRepository userDataRepository;

    public List<UserData> getAll () {
        return userDataRepository.findAll();
    }

    public UserData findById (UUID id) {
        return userDataRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Usuário não encontrado");
                });
    }

    @Transactional
    public UserData save (UserData userData) {
        return userDataRepository.save(userData);
    }

    @Transactional
    public void update (UserData userData, UUID id) {
        userDataRepository.findById(id)
                .map(existingUserData -> {
                    userData.setId(existingUserData.getId());
                    userDataRepository.save(userData);
                    return existingUserData;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Usuário não encontrado"));
    }

    @Transactional
    public void delete (UUID id) {
        userDataRepository.findById(id)
                .map(existingUserData -> {
                    userDataRepository.delete(existingUserData);
                    return existingUserData;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Usuário não encontrado"));
    }
}
