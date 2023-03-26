package br.com.amorim.supermarket.service.historicalgoodsissue;

import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.historicalgoodsissue.HistoricalGoodsIssue;
import br.com.amorim.supermarket.repository.historicalgoodsissue.HistoricalGoodsIssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class HistoricalGoodsIssueCrudServiceImpl implements HistoricalGoodsIssueCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private HistoricalGoodsIssueRepository historicalGoodsIssueRepository;
    private VerifyPageSize verifyPageSize;

    @Override
    public Page<HistoricalGoodsIssue> getAll(int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return historicalGoodsIssueRepository.findAll(pageableRequest);
    }
}
