package br.com.amorim.supermarket.service.goodsissue;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.repository.goodsissue.GoodsIssueRepository;
import br.com.amorim.supermarket.service.goodsissue.generatesalenumber.GenerateSaleNumber;
import br.com.amorim.supermarket.service.goodsissue.productissuelist.SetProductListForSale;
import br.com.amorim.supermarket.service.goodsissue.registerproduct.RegisterProduct;
import br.com.amorim.supermarket.service.goodsissue.setfieldssalesummary.SetFieldsSaleSummary;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class GoodsIssueCrudServiceImpl implements GoodsIssueCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private GoodsIssueRepository goodsIssueRepository;
    private VerifyPageSize verifyPageSize;
    private GenerateSaleNumber generateSaleNumber;
    private RegisterProduct registerProduct;
    private SetProductListForSale setProductListForSale;
    private SetFieldsSaleSummary setFieldsSaleSummary;

    @Override
    public Page<GoodsIssue> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return goodsIssueRepository.findAll(pageableRequest);
    }

    @Override
    public GoodsIssue findById (UUID id) {
        return goodsIssueRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.GOODS_ISSUE_NOT_FOUND.message));
                });
    }
    @Transactional
    @Override
    public GoodsIssue save (GoodsIssue goodsIssue) {
        setFields(goodsIssue);
        registerProduct.register(goodsIssue);
        return goodsIssueRepository.save(goodsIssue);
    }

    private void setFields(GoodsIssue goodsIssue) {
        var generate = this.generateSaleNumber.generate();
        goodsIssue.setSaleNumber(generate);
        setProductListForSale.fillProducts(goodsIssue);
        setFieldsSaleSummary.setFieldsSummary(goodsIssue);
        goodsIssue.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
        setFieldsSaleSummary.setFieldsHistoricalGoodsReceipt(goodsIssue);
    }

    @Transactional
    @Override
    public void update (GoodsIssue goodsIssue, UUID id) {
        goodsIssueRepository.findById(id)
                .map(existingGoodsIssue -> {
                    goodsIssue.setId(existingGoodsIssue.getId());
                    goodsIssueRepository.save(goodsIssue);
                    return existingGoodsIssue;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.GOODS_ISSUE_NOT_FOUND.message)));
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        goodsIssueRepository.findById(id)
                .map(existingGoodsIssue -> {
                    goodsIssueRepository.delete(existingGoodsIssue);
                    return existingGoodsIssue;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.GOODS_ISSUE_NOT_FOUND.message)));
    }
}
