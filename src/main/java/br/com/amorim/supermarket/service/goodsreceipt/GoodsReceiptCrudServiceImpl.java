package br.com.amorim.supermarket.service.goodsreceipt;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.repository.goodsreceipt.GoodsReceiptRepository;
import br.com.amorim.supermarket.service.goodsreceipt.changepurchaseamount.ChangeProductPurchaseAmount;
import br.com.amorim.supermarket.service.goodsreceipt.generatecontrolnumber.GenerateControlNumberGoodsReceipt;
import br.com.amorim.supermarket.service.goodsreceipt.productreceiptlist.SetProductList;
import br.com.amorim.supermarket.service.goodsreceipt.totalproduct.SetFieldPurchaseSummary;
import br.com.amorim.supermarket.service.goodsreceipt.verifyinvoice.VerifyInvoice;
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
public class GoodsReceiptCrudServiceImpl implements GoodsReceiptCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private GoodsReceiptRepository goodsReceiptRepository;
    private VerifyPageSize verifyPageSize;
    private GenerateControlNumberGoodsReceipt generateControlNumberGoodsReceipt;
    private ChangeProductPurchaseAmount changeProductPurchaseAmount;
    private SetProductList setProductList;
    private VerifyInvoice verifyInvoice;
    private SetFieldPurchaseSummary setFieldPurchaseSummary;

    @Override
    public Page<GoodsReceipt> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return goodsReceiptRepository.findAll(pageableRequest);
    }

    @Override
    public GoodsReceipt findById (UUID id) {
        return goodsReceiptRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.GOODS_RECEIPT_NOT_FOUND.message));
                });
    }
    @Transactional
    @Override
    public GoodsReceipt save (GoodsReceipt goodsReceipt) {
        verifyInvoice.verifyInvoiceInDatabase(goodsReceipt);
        setFieldsForSave(goodsReceipt);
        return goodsReceiptRepository.save(goodsReceipt);
    }

    private void setFieldsForSave(GoodsReceipt goodsReceipt) {
        var generateRegisterNumber = generateControlNumberGoodsReceipt.generate(goodsReceipt);
        goodsReceipt.setControlNumber(generateRegisterNumber);
        goodsReceipt.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
        setProductList.setProduct(goodsReceipt);
        changeProductPurchaseAmount.changePurchasePrice(goodsReceipt);
        setFieldPurchaseSummary.calculateTotalProducts(goodsReceipt);
        goodsReceipt.setReceived(true);
        setFieldPurchaseSummary.setFieldsHistoricalGoodsReceipt(goodsReceipt);
    }

    @Transactional
    @Override
    public void update (GoodsReceipt goodsReceipt, UUID id) {
        goodsReceiptRepository.findById(id)
                .map(existingGoodsReceipt -> {
                    goodsReceipt.setId(existingGoodsReceipt.getId());
                    setFieldsForUpdate(goodsReceipt, existingGoodsReceipt);
                    goodsReceiptRepository.save(goodsReceipt);
                    return existingGoodsReceipt;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.GOODS_RECEIPT_NOT_FOUND.message)));
    }

    private void setFieldsForUpdate(GoodsReceipt goodsReceipt, GoodsReceipt existentGoodsReceipt) {
        goodsReceipt.setControlNumber(existentGoodsReceipt.getControlNumber());
        goodsReceipt.setRegistrationDate(Timestamp.valueOf(LocalDateTime.now()));
        setProductList.setProduct(goodsReceipt);
        changeProductPurchaseAmount.changePurchasePrice(goodsReceipt);
        setFieldPurchaseSummary.calculateTotalProducts(goodsReceipt);
        goodsReceipt.setReceived(true);
        setFieldPurchaseSummary.setFieldsHistoricalGoodsReceipt(goodsReceipt);
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        goodsReceiptRepository.findById(id)
                .map(existingGoodsReceipt -> {
                    goodsReceiptRepository.delete(existingGoodsReceipt);
                    return existingGoodsReceipt;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.GOODS_RECEIPT_NOT_FOUND.message)));
    }
}
