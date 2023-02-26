package br.com.amorim.supermarket.service.subsection.generateinternalcode;

import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.generateinternalcoderepositorycustom.GenerateInternalCodeSubSectionRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.subsection.SubSectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GenerateInternalCodeSubSectionImplTest {

    @InjectMocks
    private GenerateInternalCodeSubSectionImpl generateInternalCodeSubSection;
    @Mock
    private GenerateInternalCodeSubSectionRepositoryCustom generateInternalCodeSubSectionRepositoryCustom;

    private static final BigInteger INTERNAL_CODE_ONE = BigInteger.valueOf(1);
    private static final BigInteger INTERNAL_CODE_TWO = BigInteger.valueOf(2);
    private static final BigInteger INTERNAL_CODE_THREE = BigInteger.valueOf(3);
    private SubSection subSection1;
    private SubSection subSection2;
    private SubSection subSection3;

    private void startSubSection() {
        var subSectionTest1 = new SubSectionTest();
        var subSectionTest2 = new SubSectionTest();
        var subSectionTest3 = new SubSectionTest();
        subSection1 = subSectionTest1.generateSubsection();
        subSection2 = subSectionTest2.generateSubsection();
        subSection3 = subSectionTest3.generateSubsection();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startSubSection();
    }

    @Test
    void shouldGenerateANewCodeWhenSave() {
        when(generateInternalCodeSubSectionRepositoryCustom.generateInternalCode(subSection1))
                .thenReturn(INTERNAL_CODE_ONE);
        when(generateInternalCodeSubSectionRepositoryCustom.generateInternalCode(subSection2))
                .thenReturn(INTERNAL_CODE_TWO);
        when(generateInternalCodeSubSectionRepositoryCustom.generateInternalCode(subSection3))
                .thenReturn(INTERNAL_CODE_THREE);
        var generateInternalCode = generateInternalCodeSubSection.generate(subSection3);

        assertEquals(INTERNAL_CODE_THREE, generateInternalCode);
    }
}