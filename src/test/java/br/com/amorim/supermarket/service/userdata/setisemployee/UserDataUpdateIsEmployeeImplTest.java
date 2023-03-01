package br.com.amorim.supermarket.service.userdata.setisemployee;

import br.com.amorim.supermarket.model.userdata.UserData;
import br.com.amorim.supermarket.repository.userdata.UserDataRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata.UserDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserDataUpdateIsEmployeeImplTest {

    @InjectMocks
    private UserDataUpdateIsEmployeeImpl userDataUpdateIsEmployee;
    @Mock
    private UserDataRepository userDataRepository;

    private UserData userData;

    private void startUserData() {
        var userDataTest = new UserDataTest();
        userData = userDataTest.generateUserData();
    }
    @BeforeEach
    void setUp() {
        openMocks(this);
        startUserData();
    }

    @Test
    void shouldSetIsEmployeeWithFalse() {
        when(userDataRepository.findById(userData.getId()))
                .thenReturn(Optional.of(userData));
        userDataUpdateIsEmployee.isUserEmployee(userData);

        assertEquals(false, userData.getIsEmployee());
    }
}