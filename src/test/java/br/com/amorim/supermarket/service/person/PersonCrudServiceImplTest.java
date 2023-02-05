package br.com.amorim.supermarket.service.person;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.person.Person;
import br.com.amorim.supermarket.repository.person.PersonRepository;
import br.com.amorim.supermarket.service.person.verifycpf.VerifyPersonCpf;
import br.com.amorim.supermarket.service.person.verifyrg.VerifyPersonRg;
import br.com.amorim.supermarket.service.person.verifyuserdata.VerifyPersonUserData;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.person.PersonTest;
import org.junit.jupiter.api.Assertions;
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

class PersonCrudServiceImplTest {

    @InjectMocks
    private PersonCrudServiceImpl personCrudService;
    @Mock
    private PersonRepository personRepositoryMock;
    @Mock
    private VerifyPageSize verifyPageSizeMock;
    @Mock
    private VerifyPersonCpf verifyPersonCpfMock;
    @Mock
    private VerifyPersonRg verifyPersonRgMock;
    @Mock
    private VerifyPersonUserData verifyPersonUserDataMock;

    private Person person1;
    private static final String MESSAGE_ERROR = getString(MessagesKeyType.PERSON_DATA_NOT_FOUND.message);
    private static final String UNKNOWN_ID = "0eb5c7e2-b35c-44fa-a8cb-b5d91447da82";
    private ArgumentCaptor<UUID> knownIdCapture;

    private void startPerson() {
        PersonTest personTest1 = new PersonTest();
        person1 = personTest1.generatePerson();
        knownIdCapture = ArgumentCaptor.forClass(UUID.class);
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startPerson();
    }

    @Test
    void shouldFindByIdWithSuccess() {
        when(personRepositoryMock.findById(person1.getId()))
                .thenReturn(Optional.of(person1));

        var findEstablishment = personCrudService
                .findById(person1.getId());

        assertNotNull(findEstablishment);
        assertEquals(person1.getId(), findEstablishment.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdIsUnknown() {
        when(personRepositoryMock.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(person1));

        String exceptionMessage = Assertions.assertThrows(
                NotFoundException.class, () -> {
                    personCrudService.findById(person1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> personCrudService
                .findById(person1.getId()));

    }

    @Test
    void shouldSaveWithSuccess() {
        when(personRepositoryMock.save(person1))
                .thenReturn(person1);
        var savePerson = personCrudService.save(person1);
        assertNotNull(savePerson);
        assertEquals(Person.class, person1.getClass());
    }

    @Test
    void shouldUpdateWithSuccess() {
        when(personRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(person1));
        when(personRepositoryMock.save(person1)).thenReturn(person1);

        personCrudService.update(person1, person1.getId());

        assertEquals(person1.getId(), knownIdCapture.getValue());
        assertEquals(person1, personRepositoryMock.save(person1));
        assertEquals(person1.getCpf(),
                personRepositoryMock.save(person1).getCpf());
        assertEquals(person1.getClass(),
                personRepositoryMock.save(person1).getClass());
    }

    @Test
    void shouldNotUpdateWhenIdIsUnknown() {
        when(personRepositoryMock.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(person1));

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    personCrudService.update(person1, person1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () -> personCrudService
                .update(person1, person1.getId()));

    }

    @Test
    void shouldDeleteWithSuccess() {
        when(personRepositoryMock.findById(person1.getId()))
                .thenReturn(Optional.of(person1));
        doNothing().when(personRepositoryMock).delete(person1);

        personCrudService.delete(person1.getId());

        verify(personRepositoryMock, times(1))
                .delete(person1);
    }

    @Test
    void shouldBeExactlyIdPassedAsAnArgumentWhenDelete() {
        when(personRepositoryMock.findById(knownIdCapture.capture()))
                .thenReturn(Optional.of(person1));
        doNothing().when(personRepositoryMock).delete(person1);

        personCrudService.delete(person1.getId());

        assertEquals(person1.getId(), knownIdCapture.getValue());
    }

    @Test
    void shouldNotDeleteWhenIdIsIncorrect() {
        when(personRepositoryMock.findById(UUID.fromString(UNKNOWN_ID)))
                .thenReturn(Optional.of(person1));
        doNothing().when(personRepositoryMock).delete(person1);

        String exceptionMessage = assertThrows(
                NotFoundException.class, () -> {
                    personCrudService.delete(person1.getId());
                }
        ).getMessage();
        assertEquals(MESSAGE_ERROR, exceptionMessage);
        assertThrows(NotFoundException.class, () ->
                personCrudService.delete(person1.getId()));
        verify(personRepositoryMock, times(0)).delete(person1);
    }
}