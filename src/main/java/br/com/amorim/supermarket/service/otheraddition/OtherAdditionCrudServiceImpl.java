package br.com.amorim.supermarket.service.otheraddition;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import br.com.amorim.supermarket.repository.otheraddition.OtherAdditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class OtherAdditionCrudServiceImpl implements OtherAdditionCrudService {

    private OtherAdditionRepository otherAdditionRepository;
    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private VerifyPageSize verifyPageSize;

    @Override
    public Page<OtherAddition> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return otherAdditionRepository.findAll(pageableRequest);
    }

    @Override
    public OtherAddition findById(UUID id) {
        return otherAdditionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.OTHER_ADDITION_NOT_FOUND.message));
                });
    }

    @Override
    public OtherAddition save(OtherAddition otherAddition) {
        return otherAdditionRepository.save(otherAddition);
    }

    @Override
    public void update(OtherAddition otherAddition, UUID id) {
        otherAdditionRepository.findById(id)
                .map(existingOtherAddition -> {
                    otherAddition.setId(existingOtherAddition.getId());
                    otherAdditionRepository.save(otherAddition);
                    return existingOtherAddition;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.OTHER_ADDITION_NOT_FOUND.message)));
    }

    @Override
    public void delete(UUID id) {
        otherAdditionRepository.findById(id)
                .map(existingOtherAddition -> {
                    otherAdditionRepository.delete(existingOtherAddition);
                    return existingOtherAddition;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.OTHER_ADDITION_NOT_FOUND.message)));
    }
}
