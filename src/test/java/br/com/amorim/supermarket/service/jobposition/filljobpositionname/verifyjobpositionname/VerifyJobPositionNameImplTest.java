package br.com.amorim.supermarket.service.jobposition.filljobpositionname.verifyjobpositionname;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.repository.jobposition.JobPositionRepository;
import br.com.amorim.supermarket.testutils.generateentitiesunittests.jobposition.JobPositionTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VerifyJobPositionNameImplTest {

    @InjectMocks
    private VerifyJobPositionNameImpl verifyJobPositionName;
    @Mock
    private JobPositionRepository jobPositionRepository;
    private JobPosition jobPosition;

    private void startJobPosition() {
        var jobPositionTest = new JobPositionTest();
        jobPosition = jobPositionTest.generateJobPosition();
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
        startJobPosition();
    }

    @Test
    void shouldReturnFalseWhenPositionNameNotExists() {
        when(jobPositionRepository.findByName(jobPosition.getName()))
                .thenReturn(Optional.empty());
        boolean existsInSalary = verifyJobPositionName
                .isPositionNameAlreadyExistsInSalary(jobPosition);
        assertFalse(existsInSalary);
    }

    @Test
    void shouldReturnExceptionWhenPositionNameAlreadyExists() {
        var messageExpected = getString(MessagesKeyType.JOB_POSITION_NAME_ALREADY_EXISTS.message);
        when(jobPositionRepository.findByName(jobPosition.getName()))
                .thenReturn(Optional.of(jobPosition));
        var messageException = assertThrows(BusinessRuleException.class, () ->
                verifyJobPositionName.isPositionNameAlreadyExistsInSalary(jobPosition)
        ).getMessage();
        assertEquals(messageExpected, messageException);
    }
}