package br.com.amorim.supermarket.service.mainsection.generateinternalcode;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.mainsection.generateinternalcoderepositorycustom.GenerateInternalCodeMainSectionRepositoryCustom;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.mainsection.MainSectionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GenerateInternalCodeMainSectionImplTest {

    @InjectMocks
    private GenerateInternalCodeMainSectionImpl generateInternalCodeMainSection;
    @Mock
    private GenerateInternalCodeMainSectionRepositoryCustom generateInternalCodeMainSectionRepositoryCustom;
    private static final BigInteger INTERNAL_CODE_ONE = BigInteger.valueOf(1);
    private static final BigInteger INTERNAL_CODE_TWO = BigInteger.valueOf(2);
    private static final BigInteger INTERNAL_CODE_THREE = BigInteger.valueOf(3);
    private MainSection mainSection1;
    private MainSection mainSection2;
    private MainSection mainSection3;

    private void startMainSection() {
        var mainSectionTest1 = new MainSectionTest();
        var mainSectionTest2 = new MainSectionTest();
        var mainSectionTest3 = new MainSectionTest();
        mainSection1 = mainSectionTest1.generateMainsection();
        mainSection2 = mainSectionTest2.generateMainsection();
        mainSection3 = mainSectionTest3.generateMainsection();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startMainSection();
    }

    @Test
    void shouldGenerateANewCodeWhenSave() {
        when(generateInternalCodeMainSectionRepositoryCustom.generateInternalCode(mainSection1))
                .thenReturn(INTERNAL_CODE_ONE);
        when(generateInternalCodeMainSectionRepositoryCustom.generateInternalCode(mainSection2))
                .thenReturn(INTERNAL_CODE_TWO);
        when(generateInternalCodeMainSectionRepositoryCustom.generateInternalCode(mainSection3))
                .thenReturn(INTERNAL_CODE_THREE);
        var generateInternalCode = generateInternalCodeMainSection.generate(mainSection3);

        assertEquals(INTERNAL_CODE_THREE, generateInternalCode);
    }
}