package br.com.amorim.supermarket.common.verifypagesize;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidactionexception.InvalidActionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyPageSizeImpl implements VerifyPageSize {

    @Override
    public boolean verifyPageSizeForGetAll(int page, int size) {
        if (size > 20) {
            throw new InvalidActionException(
                    getString(MessagesKeyType.COMMON_PAGE_SIZE_INVALID_PAGE_SIZE.message));
        }
        if (size < 1) {
            throw new InvalidActionException(
                    getString(MessagesKeyType.COMMON_SIZE_CANNOT_BE_LESS_THAN_ONE.message));
        }
        if (page < 0) {
            throw new InvalidActionException(
                    getString(MessagesKeyType.COMMON_PAGE_CANNOT_BE_LESS_THAN_ONE.message));
        }
        return false;
    }
}
