package br.com.amorim.supermarket.service.mainsection.verifymainsectionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.mainsection.MainSectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VerifyMainSectionNameImplTest {

    @InjectMocks
    private VerifyMainSectionNameImpl verifyMainSectionName;
    @Mock
    private MainSectionRepository mainSectionRepository;

    private MainSection mainSection1;
    private MainSection mainSection2;
    private final String EXPECTED_MESSAGE = getString(MessagesKeyType.MAIN_SECTION_NAME_ALREADY_EXISTS.message);

    private void startMainSection() {
        var mainSectionTest1 = new MainSectionTest();
        var mainSectionTest2 = new MainSectionTest();
        mainSection1 = mainSectionTest1.generateMainsection();
        mainSection2 = mainSectionTest2.generateMainsection();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startMainSection();
    }

    @Test
    void shouldReturnFalseWhenMainSectionNameNotExistsBeforeSave() {
        when(mainSectionRepository.existsByName(mainSection1.getName()))
                .thenReturn(false);

        var existsByName = verifyMainSectionName.existsByMainSectionNameBeforeSave(mainSection1);

        assertFalse(existsByName);
    }

    @Test
    void shouldReturnAExceptionMessageWhenDepartmentNameAlreadyExistsBeforeSave() {
        when(mainSectionRepository.existsByName(mainSection1.getName()))
                .thenReturn(true);

        var messageExcpetion = assertThrows(BusinessRuleException.class, () ->
                verifyMainSectionName.existsByMainSectionNameBeforeSave(mainSection1)
        ).getMessage();

        assertEquals(EXPECTED_MESSAGE, messageExcpetion);
    }

    @Test
    void shouldReturnFalseWhenDepartmentNameNotExistsBeforeUpdate() {
        List<MainSection> mainSectionList = new ArrayList<>();
        mainSectionList.add(mainSection1);
        when(mainSectionRepository.findAll()).thenReturn(mainSectionList);

        var existsByName = verifyMainSectionName.existsByMainSectionNameBeforeUpdate(mainSection1);

        assertFalse(existsByName);
    }

    @Test
    void shouldReturnAExceptionMessageWhenMainSectionNameAlreadyExistsBeforeUpdate() {
        List<MainSection> mainSectionList = new ArrayList<>();
        mainSection1.setName(mainSection2.getName());
        mainSectionList.add(mainSection2);
        when(mainSectionRepository.findAll()).thenReturn(mainSectionList);

        var messageExcpetion = assertThrows(BusinessRuleException.class, () ->
                verifyMainSectionName.existsByMainSectionNameBeforeUpdate(mainSection1)
        ).getMessage();

        assertEquals(EXPECTED_MESSAGE, messageExcpetion);
    }
}