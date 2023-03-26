package br.com.amorim.supermarket.service.historicalgoodsreceipt;

import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import br.com.amorim.supermarket.repository.historicalgoodsreceipt.HistoricalGoodsReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor

@Service
public class HistoricalGoodsReceiptCrudServiceImpl implements HistoricalGoodsReceiptCrudService {
    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private HistoricalGoodsReceiptRepository historicalGoodsReceiptRepository;
    private VerifyPageSize verifyPageSize;

    @Override
    public Page<HistoricalGoodsReceipt> getAll(int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return historicalGoodsReceiptRepository.findAll(pageableRequest);
    }
}
