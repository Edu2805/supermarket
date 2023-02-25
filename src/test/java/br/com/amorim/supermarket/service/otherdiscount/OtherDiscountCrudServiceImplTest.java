package br.com.amorim.supermarket.service.otherdiscount;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import br.com.amorim.supermarket.repository.otherdiscount.OtherDiscountRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.otherdiscount.OtherDiscountTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class OtherDiscountCrudServiceImplTest {

    @InjectMocks
    private OtherDiscountCrudServiceImpl otherDiscountCrudService;
    @Mock
    private OtherDiscountRepository otherDiscountRepository;
    private OtherDiscount otherDiscount;
    ArgumentCaptor<UUID> knownIdCapture;
    public static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
    public static final String MESSAGE_ERROR = getString(MessagesKeyType.OTHER_DISCOUNT_NOT_FOUND.message);

    private void startOtherDiscount() {
        var otherDiscountTest = new OtherDiscountTest();
        otherDiscount = otherDiscountTest.generateOtherDiscount();
        knownIdCapture = ArgumentCaptor.forClass(UUID.class);
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startOtherDiscount();
    }

    @Test
    void shouldFindById() {
        when(otherDiscountRepository.findById(otherDiscount.getId()))
                .thenReturn(Optional.of(otherDiscount));
        var findByIdOtherDiscount = otherDiscountCrudService.findById(otherDiscount.getId());

        assertNotNull(findByIdOtherDiscount);
        assertEquals(otherDiscount.getId(), findByIdOtherDiscount.getId());
    }

    @Test
    void shouldNotFindById() {
        when(otherDiscountRepository.findById(otherDiscount.getId()))
                .thenReturn(Optional.empty());
        var exceptionMessage = assertThrows(NotFoundException.class, () ->
                otherDiscountCrudService.findById(otherDiscount.getId())
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
    }

    @Test
    void shouldSaveWithSuccess() {
        when(otherDiscountRepository.save(otherDiscount))
                .thenReturn(otherDiscount);
        var saveOtherDiscount = otherDiscountCrudService.save(otherDiscount);
        assertNotNull(saveOtherDiscount);
        assertEquals(OtherDiscount.class, saveOtherDiscount.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        when(otherDiscountRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(otherDiscount));
        when(otherDiscountRepository.save(otherDiscount))
                .thenReturn(otherDiscount);

        otherDiscountCrudService.update(otherDiscount, otherDiscount.getId());

        assertEquals(otherDiscount.getId(), knownIdCapture.getValue());
        assertEquals(otherDiscount, otherDiscountRepository.save(otherDiscount));
        assertEquals(otherDiscount.getClass(),
                otherDiscountRepository.save(otherDiscount).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(otherDiscountRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(otherDiscount));

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    otherDiscountCrudService.update(otherDiscount, otherDiscount.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> otherDiscountCrudService
                .update(otherDiscount, otherDiscount.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(otherDiscountRepository.findById(otherDiscount.getId()))
                .thenReturn(Optional.of(otherDiscount));
        doNothing().when(otherDiscountRepository).delete(otherDiscount);

        otherDiscountCrudService.delete(otherDiscount.getId());

        verify(otherDiscountRepository, times(1))
                .delete(otherDiscount);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        when(otherDiscountRepository.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(otherDiscount));
        doNothing().when(otherDiscountRepository).delete(otherDiscount);

        otherDiscountCrudService.delete(otherDiscount.getId());

        assertEquals(otherDiscount.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(otherDiscountRepository.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(otherDiscount));
        doNothing().when(otherDiscountRepository).delete(otherDiscount);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    otherDiscountCrudService.delete(otherDiscount.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                otherDiscountCrudService.delete(otherDiscount.getId()));
        verify(otherDiscountRepository, times(0)).delete(otherDiscount);
    }
}