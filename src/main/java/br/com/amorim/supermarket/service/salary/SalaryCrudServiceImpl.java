package br.com.amorim.supermarket.service.salary;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.salary.Salary;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import br.com.amorim.supermarket.repository.salary.salaryrepositorycustom.SalaryRepositoryCustom;
import br.com.amorim.supermarket.service.salary.calculatesalary.CalculateSalary;
import br.com.amorim.supermarket.service.salary.verifyduplicatesalary.VerifyDuplicateSalary;
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
public class SalaryCrudServiceImpl implements SalaryCrudService {

    private SalaryRepository salaryRepository;
    private SalaryRepositoryCustom salaryRepositoryCustom;
    private VerifyDuplicateSalary verifyDuplicateSalary;
    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private VerifyPageSize verifyPageSize;
    private CalculateSalary calculateSalary;

    @Override
    public Page<Salary> getAll(int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return salaryRepository.findAll(pageableRequest);
    }

    @Override
    public Salary findById (UUID id) {
        return salaryRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.SALARY_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public Salary save (Salary salary) {
        verifyDuplicateSalary.isDuplicateSalaryBeforeSave(salary);
        calculateSalary.calculate(salary);
        salary.getOtherAdditions().forEach(otherAddition -> otherAddition.setSalary(salary));
        salary.getOtherDiscounts().forEach(otherDiscount -> otherDiscount.setSalary(salary));
        return salaryRepository.save(salary);
    }

    @Transactional
    @Override
    public void update (Salary salary, UUID id) {
        salaryRepository.findById(id)
                .map(existingSalary -> {
                    salary.setId(existingSalary.getId());
                    salary.getOtherAdditions().forEach(otherAddition -> otherAddition.setSalary(salary));
                    salary.getOtherDiscounts().forEach(otherDiscount -> otherDiscount.setSalary(salary));
                    verifyDuplicateSalary.isDuplicateSalaryBeforeUpdate(salary);
                    calculateSalary.calculate(salary);
                    salaryRepository.save(salary);
                    return existingSalary;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.SALARY_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        salaryRepository.findById(id)
                .map(existingSalary -> {
                    salaryRepository.delete(existingSalary);
                    return existingSalary;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.SALARY_NOT_FOUND.message)));
    }

    @Override
    public List<Salary> isThereASalaryAlreadyRegisteredForTheJobPosition() {
        return salaryRepositoryCustom.existsSalaryInJobPosition();
    }
}
