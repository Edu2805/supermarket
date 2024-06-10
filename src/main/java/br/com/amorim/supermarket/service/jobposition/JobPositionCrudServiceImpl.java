package br.com.amorim.supermarket.service.jobposition;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.repository.jobposition.JobPositionRepository;
import br.com.amorim.supermarket.repository.jobposition.jobpositionrepositorycustom.JobPositionRepositoryCustom;
import br.com.amorim.supermarket.service.jobposition.filljobpositionname.fillname.FillPositionNameBySalary;
import br.com.amorim.supermarket.service.jobposition.filljobpositionname.verifyjobpositionname.VerifyJobPositionName;
import br.com.amorim.supermarket.service.jobposition.generateinternalcode.GenerateInternalCodeJobPosition;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class JobPositionCrudServiceImpl implements JobPositionCrudService {

    private JobPositionRepository jobPositionRepository;
    private GenerateInternalCodeJobPosition generateInternalCodeJobPosition;
    private JobPositionRepositoryCustom jobPositionRepositoryCustom;
    private VerifyJobPositionName verifyJobPositionName;
    private FillPositionNameBySalary fillPositionNameBySalary;
    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private VerifyPageSize verifyPageSize;

    @Override
    public Page<JobPosition> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return jobPositionRepository.findAll(pageableRequest);
    }

    @Override
    public JobPosition findById (UUID id) {
        return jobPositionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.JOB_POSITION_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public JobPosition save (JobPosition jobPosition) {
        fillPositionNameBySalary.fillPositionName(jobPosition);
        verifyJobPositionName.isPositionNameAlreadyExistsInSalary(jobPosition);
        setInternalCode(jobPosition);
        return jobPositionRepository.save(jobPosition);
    }

    private void setInternalCode (JobPosition jobPosition) {
        var incrementInternalCode = generateInternalCodeJobPosition.generate(jobPosition);
        jobPosition.setCode(incrementInternalCode);
    }

    @Transactional
    @Override
    public void update (JobPosition jobPosition, UUID id) {
        jobPositionRepository.findById(id)
                .map(existingJobPosition -> {
                    jobPosition.setId(existingJobPosition.getId());
                    jobPosition.setName(existingJobPosition.getName());
                    jobPosition.setCode(existingJobPosition.getCode());
                    fillPositionNameBySalary.fillPositionName(jobPosition);
                    jobPositionRepository.save(jobPosition);
                    return existingJobPosition;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.JOB_POSITION_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        jobPositionRepository.findById(id)
                .map(existingJobPosition -> {
                    jobPositionRepository.delete(existingJobPosition);
                    return existingJobPosition;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.JOB_POSITION_NOT_FOUND.message)));
    }

    @Override
    public List<JobPosition> isThereAJobPositionAlreadyRegisteredForTheEmployee() {
        return jobPositionRepositoryCustom.existsJobPositionInEmployee();
    }
}
