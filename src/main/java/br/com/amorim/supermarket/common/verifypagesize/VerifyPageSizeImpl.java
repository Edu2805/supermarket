package br.com.amorim.supermarket.common.verifypagesize;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidactionexception.InvalidActionException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class VerifyPageSizeImpl implements VerifyPageSize {

    private static final int MAX_PAGE_SIZE = 20;
    private static final int MINIMUM_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;

    @Override
    public boolean verifyPageSizeForGetAll(int page, int size) {
        if (size > MAX_PAGE_SIZE) {
            throw new InvalidActionException(
                    getString(MessagesKeyType.COMMON_PAGE_SIZE_INVALID_PAGE_SIZE.message));
        }
        if (size < MINIMUM_PAGE_SIZE) {
            throw new InvalidActionException(
                    getString(MessagesKeyType.COMMON_SIZE_CANNOT_BE_LESS_THAN_ONE.message));
        }
        if (page < ZERO_PAGE_SIZE) {
            throw new InvalidActionException(
                    getString(MessagesKeyType.COMMON_PAGE_CANNOT_BE_LESS_THAN_ONE.message));
        }
        return false;
    }
}
