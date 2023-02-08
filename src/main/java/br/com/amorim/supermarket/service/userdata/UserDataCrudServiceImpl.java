package br.com.amorim.supermarket.service.userdata;

import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.service.userdata.verifyusername.VerifyUserName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class UserDataCrudServiceImpl implements UserDataCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private UserDataRepository userDataRepository;
    private VerifyPageSize verifyPageSize;
    private VerifyUserName verifyUserName;

    @Override
    public Page<UserData> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return userDataRepository.findAll(pageableRequest);
    }

    @Override
    public UserData findById (UUID id) {
        return userDataRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Usuário não encontrado");
                });
    }

    @Transactional
    @Override
    public UserData save (UserData userData) {
        verifyUserName.verifyUserDataBeforeSave(userData);
        userData.setRegistrationDate(Timestamp.from(Instant.now()));
        return userDataRepository.save(userData);
    }

    @Transactional
    @Override
    public void update (UserData userData, UUID id) {
        userDataRepository.findById(id)
                .map(existingUserData -> {
                    userData.setId(existingUserData.getId());
                    verifyUserName.verifyUserDataBeforeUpdate(userData);
                    userData.setRegistrationDate(Timestamp.from(Instant.now()));
                    userDataRepository.save(userData);
                    return existingUserData;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Usuário não encontrado"));
    }

    @Transactional
    @Override
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
