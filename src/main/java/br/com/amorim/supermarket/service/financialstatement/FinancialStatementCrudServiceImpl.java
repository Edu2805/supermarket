package br.com.amorim.supermarket.service.financialstatement;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.financialstatement.FinancialStatement;
import br.com.amorim.supermarket.repository.financialstatement.FinancialStatementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class FinancialStatementCrudServiceImpl implements FinancialStatementCrudService{

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private FinancialStatementRepository financialStatementRepository;
    private VerifyPageSize verifyPageSize;

    @Override
    public Page<FinancialStatement> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return financialStatementRepository.findAll(pageableRequest);
    }

    @Override
    public FinancialStatement findById (UUID id) {
        return financialStatementRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.FINANCIAL_STATEMENT_NOT_FOUND.message));
                });
    }
    @Transactional
    @Override
    public FinancialStatement save (FinancialStatement financialStatement) {
        return financialStatementRepository.save(financialStatement);
    }

    @Transactional
    @Override
    public void update (FinancialStatement financialStatement, UUID id) {
        financialStatementRepository.findById(id)
                .map(existingFinancialStatement -> {
                    financialStatement.setId(existingFinancialStatement.getId());
                    financialStatementRepository.save(financialStatement);
                    return existingFinancialStatement;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.FINANCIAL_STATEMENT_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        financialStatementRepository.findById(id)
                .map(existingFinancialStatement -> {
                    financialStatementRepository.delete(existingFinancialStatement);
                    return existingFinancialStatement;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.FINANCIAL_STATEMENT_NOT_FOUND.message)));
    }
}
