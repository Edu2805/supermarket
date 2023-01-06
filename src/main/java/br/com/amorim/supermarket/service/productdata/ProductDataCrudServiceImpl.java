package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCode;
import br.com.amorim.supermarket.service.productdata.productvalidator.ProductValidatorEan13OrDun14;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

/**
 * Implementação do CRUD de ProductData
 */

@AllArgsConstructor

@Service
public class ProductDataCrudServiceImpl implements ProductDataCrudService {

    private ProductDataRepository productDataRepository;
    private ProductValidatorEan13OrDun14 validateEan13OrDun14;
    private CalculateMargin calculateMargin;
    private GenerateInternalCode generateInternalCode;


    @Override
    public List<ProductData> getAll() {
        return productDataRepository.findAll();
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
