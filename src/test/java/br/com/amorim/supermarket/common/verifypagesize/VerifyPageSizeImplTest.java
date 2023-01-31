package br.com.amorim.supermarket.common.verifypagesize;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invalidactionexception.InvalidActionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class VerifyPageSizeImplTest {

    @InjectMocks
    private VerifyPageSizeImpl verifyPageSize;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldShowAInvalidActionExceptionMessageWhenPageSizeIsGreaterThanTwenty() {
        int page = 1;
        int size = 21;
        var errorMessage = getString(MessagesKeyType.COMMON_PAGE_SIZE_INVALID_PAGE_SIZE.message);

        String exceptionMessage = Assertions.assertThrows(
                InvalidActionException.class, () -> {
                    verifyPageSize.verifyPageSizeForGetAll(page, size);
                }
        ).getMessage();
        assertEquals(errorMessage, exceptionMessage);
        assertThrows(InvalidActionException.class, () ->
                verifyPageSize.verifyPageSizeForGetAll(page, size));
    }

    @Test
    void shouldReturnTrueWhenPageSizeIsLessOrEqualsThanTwenty() {
        int page = 1;
        int size = 20;
        var lessThanTwenty = verifyPageSize.verifyPageSizeForGetAll(page, size);
        assertFalse(lessThanTwenty);
    }
}