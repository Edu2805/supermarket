package br.com.amorim.supermarket.service.otherdiscount;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.repository.otherdiscount.OtherDiscountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class OtherDiscountCrudServiceImpl implements OtherDiscountCrudService {

    private OtherDiscountRepository otherDiscountRepository;
    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private VerifyPageSize verifyPageSize;

    @Override
    public Page<OtherDiscount> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return otherDiscountRepository.findAll(pageableRequest);
    }

    @Override
    public OtherDiscount findById(UUID id) {
        return otherDiscountRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.OTHER_DISCOUNT_NOT_FOUND.message));
                });
    }

    @Override
    public OtherDiscount save(OtherDiscount otherDiscount) {
        return otherDiscountRepository.save(otherDiscount);
    }

    @Override
    public void update(OtherDiscount otherDiscount, UUID id) {
        otherDiscountRepository.findById(id)
                .map(existingOtherDiscount -> {
                    otherDiscount.setId(existingOtherDiscount.getId());
                    otherDiscountRepository.save(otherDiscount);
                    return existingOtherDiscount;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.OTHER_DISCOUNT_NOT_FOUND.message)));
    }

    @Override
    public void delete(UUID id) {
        otherDiscountRepository.findById(id)
                .map(existingOtherDiscount -> {
                    otherDiscountRepository.delete(existingOtherDiscount);
                    return existingOtherDiscount;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.OTHER_DISCOUNT_NOT_FOUND.message)));
    }
}
