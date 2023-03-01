package br.com.amorim.supermarket.service.subsection.verifysubsectionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
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

class VerifySubSectionNameImplTest {

    @InjectMocks
    private VerifySubSectionNameImpl verifySubSectionName;
    @Mock
    private SubSectionRepository subSectionRepository;

    private SubSection subSection1;
    private SubSection subSection2;
    private final String EXPECTED_MESSAGE = getString(MessagesKeyType.SUB_SECTION_NAME_ALREADY_EXISTS.message);

    private void startMainSection() {
        var subSectionTest1 = new SubSectionTest();
        var subSectionTest2 = new SubSectionTest();
        subSection1 = subSectionTest1.generateSubsection();
        subSection2 = subSectionTest2.generateSubsection();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startMainSection();
    }

    @Test
    void shouldReturnFalseWhenSubSectionNameNotExistsBeforeSave() {
        when(subSectionRepository.existsByName(subSection1.getName()))
                .thenReturn(false);

        var existsByName = verifySubSectionName.existsBySubSectionNameBeforeSave(subSection1);

        assertFalse(existsByName);
    }

    @Test
    void shouldReturnAExceptionMessageWhenSubSectionNameAlreadyExistsBeforeSave() {
        when(subSectionRepository.existsByName(subSection1.getName()))
                .thenReturn(true);

        var messageExcpetion = assertThrows(BusinessRuleException.class, () ->
                verifySubSectionName.existsBySubSectionNameBeforeSave(subSection1)
        ).getMessage();

        assertEquals(EXPECTED_MESSAGE, messageExcpetion);
    }

    @Test
    void shouldReturnFalseWhenSubSectionNameNotExistsBeforeUpdate() {
        List<SubSection> subSectionList = new ArrayList<>();
        subSectionList.add(subSection1);
        when(subSectionRepository.findAll()).thenReturn(subSectionList);

        var existsByName = verifySubSectionName.existsBySubSectionNameBeforeUpdate(subSection1);

        assertFalse(existsByName);
    }

    @Test
    void shouldReturnAExceptionMessageWhenSubSectionNameAlreadyExistsBeforeUpdate() {
        List<SubSection> subSectionList = new ArrayList<>();
        subSection1.setName(subSection2.getName());
        subSectionList.add(subSection2);
        when(subSectionRepository.findAll()).thenReturn(subSectionList);

        var messageExcpetion = assertThrows(BusinessRuleException.class, () ->
                verifySubSectionName.existsBySubSectionNameBeforeUpdate(subSection1)
        ).getMessage();

        assertEquals(EXPECTED_MESSAGE, messageExcpetion);
    }
}