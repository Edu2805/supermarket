package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.attachment.AttachmentRepository;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.service.common.utils.ImageUtil;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCodeProduct;
import br.com.amorim.supermarket.service.productdata.validateean13anddun14.ValidateProductEan13OrDun14;
import br.com.amorim.supermarket.service.productdata.validateproviderproduct.ValidateProductProviderProduct;
import br.com.amorim.supermarket.service.productdata.validatesubsection.ValidateProductSubSection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class ProductDataCrudServiceImpl implements ProductDataCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private ProductDataRepository productDataRepository;
    private AttachmentRepository attachmentRepository;
    private ValidateProductEan13OrDun14 validateEan13OrDun14;
    private ValidateProductSubSection validateProductSubSection;
    private ValidateProductProviderProduct validateProductProviderProduct;
    private CalculateMargin calculateMargin;
    private GenerateInternalCodeProduct generateInternalCode;
    private VerifyPageSize verifyPageSize;


    @Override
    public Page<ProductData> getAll(int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
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
        setMargin(productData);
        setInternalCode(productData);
        setPhotoAndInsert(productData);
        return productDataRepository.save(productData);
    }

    private void setPhotoAndInsert(ProductData productData) {
        if (productData.getProductPhoto() != null) {
            var imageData = productData.getProductPhoto().getImageData();
            productData.getProductPhoto().setImageData(ImageUtil.compressFile(imageData));
            attachmentRepository.save(productData.getProductPhoto());
        }
    }

    private void setMargin (ProductData productData) {
        BigDecimal margin = calculateMargin.calculateWhenInsert(productData);
        productData.setMargin(margin);
    }

    private void setInternalCode (ProductData productData) {
        BigInteger incrementInternalCode = generateInternalCode.generate(productData);
        productData.setCode(incrementInternalCode);
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
                    validateBeforeUpdate(productData);
                    BigDecimal margin = calculateMargin.calculateWhenUpdate(productData);
                    productData.setMargin(margin);
                    productData.setCode(existingProduct.getCode());
                    setPhotoAndUpdate(productData, existingProduct);
                    productDataRepository.save(productData);
                    return existingProduct;
                }).orElseThrow(() ->
                                new NotFoundException(
                                        getString(MessagesKeyType.PRODUCT_DATA_NOT_FOUND.message)));
    }

    private void setPhotoAndUpdate(ProductData productDataUpdatePhoto, ProductData productDataExistentPhoto) {
        if (productDataExistentPhoto.getProductPhoto() == null) {
            var imageData = productDataUpdatePhoto.getProductPhoto().getImageData();
            productDataUpdatePhoto.getProductPhoto().setImageData(ImageUtil.compressFile(imageData));
            attachmentRepository.save(productDataUpdatePhoto.getProductPhoto());
        } else {
            if (!productDataExistentPhoto.getProductPhoto().getId().equals(productDataUpdatePhoto.getProductPhoto().getId())) {
                deletePhoto(productDataExistentPhoto.getProductPhoto());
                var imageData = productDataUpdatePhoto.getProductPhoto().getImageData();
                productDataUpdatePhoto.getProductPhoto().setImageData(ImageUtil.compressFile(imageData));
                attachmentRepository.save(productDataUpdatePhoto.getProductPhoto());
            }
        }
    }

    private void deletePhoto(Attachment attachment) {
        if (attachment != null) {
            attachmentRepository.delete(attachment);
        }
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

    @Override
    public List<ProductData> filterProducts(ProductData filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        return productDataRepository.findAll(example);
    }
}
