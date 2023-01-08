package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCode;
import br.com.amorim.supermarket.service.productdata.productvalidator.ProductValidatorEan13OrDun14;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@AllArgsConstructor

@Service
public class ProductDataCrudServiceImpl implements ProductDataCrudService {

    private ProductDataRepository productDataRepository;
    private ProductValidatorEan13OrDun14 validateEan13OrDun14;
    private CalculateMargin calculateMargin;
    private GenerateInternalCode generateInternalCode;


    @Override
    public Page<ProductData> getAll() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "internalCode");
        return new PageImpl<>(
                productDataRepository.findAll(),
                pageRequest, size);
    }

    @Override
    public ProductData findById(UUID id) {
        return productDataRepository.findById(id)
                .orElseThrow(() -> {
                   throw new NotFoundException(
                           "Produto não encontrado");
                });
    }

    @Transactional
    @Override
    public ProductData save (ProductData productData) {
        validateEan13OrDun14.validate(productData);
        BigDecimal margin = calculateMargin.calculate(productData);
        BigInteger incrementInternalCode = generateInternalCode.generate(productData);
        productData.setMargin(margin);
        productData.setInternalCode(incrementInternalCode);
        return productDataRepository.save(productData);
    }

    @Transactional
    @Override
    public void update (ProductData productData, UUID id) {
        productDataRepository.findById(id)
                .map(existingProduct -> {
                    productData.setId(existingProduct.getId());
                    productDataRepository.save(productData);
                    return existingProduct;
                }).orElseThrow(() ->
                                new NotFoundException(
                                        "Produto não encontrado"));

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
                                "Produto não encontrado"));
    }
}
