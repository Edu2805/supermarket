package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMarginImpl;
import br.com.amorim.supermarket.service.productdata.productvalidator.WhenCreateWithOutNameImpl;
import br.com.amorim.supermarket.service.productdata.productvalidator.WhenCreateWithoutProviderImpl;
import br.com.amorim.supermarket.service.productdata.productvalidator.WhenCreateWithoutSubSectionImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class ProductDataCrudServiceImpl implements ProductDataCrudService{

    private ProductDataReposotiry productDataReposotiry;
    private WhenCreateWithOutNameImpl whenCreateWithOutName;
    private WhenCreateWithoutProviderImpl whenCreateWithoutProvider;
    private WhenCreateWithoutSubSectionImpl whenCreateWithoutSubSection;
    private CalculateMarginImpl calculateMargin;

    @Override
    public List<ProductData> getAll() {
        return productDataReposotiry.findAll();
    }

    @Override
    public ProductData findById(UUID id) {
        return productDataReposotiry.findById(id)
                .orElseThrow(() -> {
                   throw new ResponseStatusException(NOT_FOUND,
                           "Produto não encontrado");
                });
    }

    @Transactional
    @Override
    public ProductData save (ProductData productData) {
        // todo Criar um Handler de erros
        // todo gerenciar o código interno
        if (!whenCreateWithoutSubSection.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui uma subseção cadastrada");
        }
        if (!whenCreateWithoutProvider.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui um fornecedor cadastrado");
        }
        if (whenCreateWithOutName.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem o nome.");
        }
        BigDecimal margin = calculateMargin.calculate(productData);
        productData.setMargin(margin);
        productData.setInternalCode(BigInteger.valueOf(4));
        return productDataReposotiry.save(productData);
    }

    @Transactional
    @Override
    public void update (ProductData productData, UUID id) {
        productDataReposotiry.findById(id)
                .map(existingProduct -> {
                    productData.setId(existingProduct.getId());
                    productDataReposotiry.save(productData);
                    return existingProduct;
                }).orElseThrow(() ->
                                new ResponseStatusException(NOT_FOUND,
                                        "Produto não encontrado"));

    }

    @Transactional
    @Override
    public void delete (UUID id) {
        productDataReposotiry.findById(id)
                .map(product -> {
                    productDataReposotiry.delete(product);
                    return product;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado"));
    }
}
