package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCode;
import br.com.amorim.supermarket.service.productdata.validateean13anddun14.ValidateProductEan13OrDun14;
import br.com.amorim.supermarket.service.productdata.validateproviderproduct.ValidateProductProviderProduct;
import br.com.amorim.supermarket.service.productdata.validatesubsection.ValidateProductSubSection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class ProductDataCrudServiceImpl implements ProductDataCrudService {

    private ProductDataRepository productDataRepository;
    private ValidateProductEan13OrDun14 validateEan13OrDun14;
    private ValidateProductSubSection validateProductSubSection;
    private ValidateProductProviderProduct validateProductProviderProduct;
    private CalculateMargin calculateMargin;
    private GenerateInternalCode generateInternalCode;


    @Override
    public Page<ProductData> getAll(int page, int size) {
        if (page > 0)
            page -= 1;

        Pageable pageableRequest = PageRequest.of(page, size);
        return productDataRepository.findAll(pageableRequest);
    }

    @Override
    public ProductData findById(UUID id) {
        return productDataRepository.findById(id)
                .orElseThrow(() -> {
                   throw new NotFoundException(
                           getString(MessagesKeyType.PRODUCT_DATA_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public ProductData save (ProductData productData) {
        validateBeforeSave(productData);
        BigDecimal margin = calculateMargin.calculate(productData);
        BigInteger incrementInternalCode = generateInternalCode.generate(productData);
        productData.setMargin(margin);
        productData.setInternalCode(incrementInternalCode);
        return productDataRepository.save(productData);
    }

    private void validateBeforeSave(ProductData productData) {
        validateEan13OrDun14.validateBeforeSave(productData);
        validateProductSubSection.validate(productData);
        validateProductProviderProduct.validate(productData);
    }

    @Transactional
    @Override
    public void update (ProductData productData, UUID id) {
        productDataRepository.findById(id)
                .map(existingProduct -> {
                    productData.setId(existingProduct.getId());
                    validateBeforeUpdate(existingProduct);
                    BigDecimal margin = calculateMargin.calculate(existingProduct);
                    BigInteger incrementInternalCode = generateInternalCode.generate(existingProduct);
                    productData.setMargin(margin);
                    productData.setInternalCode(incrementInternalCode);
                    productDataRepository.save(productData);
                    return existingProduct;
                }).orElseThrow(() ->
                                new NotFoundException(
                                        getString(MessagesKeyType.PRODUCT_DATA_NOT_FOUND.message)));
    }

    private void validateBeforeUpdate(ProductData productData) {
        validateEan13OrDun14.validateBeforeUpdate(productData);
        validateProductSubSection.validate(productData);
        validateProductProviderProduct.validate(productData);
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        productDataRepository.findById(id)
                .map(product -> {
                    productDataRepository.delete(product);
                    return product;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.PRODUCT_DATA_NOT_FOUND.message)));
    }
}
