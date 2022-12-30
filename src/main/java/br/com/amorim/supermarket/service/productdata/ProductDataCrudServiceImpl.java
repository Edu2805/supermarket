package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCode;
import br.com.amorim.supermarket.service.productdata.productvalidator.WhenCreateWithoutName;
import br.com.amorim.supermarket.service.productdata.productvalidator.WhenCreateWithoutProvider;
import br.com.amorim.supermarket.service.productdata.productvalidator.WhenCreateWithoutSubSection;
import br.com.amorim.supermarket.service.productdata.productvalidator.WhenCreateWithoutUnity;
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

/**
 * Implementação do CRUD de ProductData
 */

@AllArgsConstructor

@Service
public class ProductDataCrudServiceImpl implements ProductDataCrudService {

    private ProductDataReposotiry productDataReposotiry;
    private WhenCreateWithoutName whenCreateWithOutName;
    private WhenCreateWithoutProvider whenCreateWithoutProvider;
    private WhenCreateWithoutSubSection whenCreateWithoutSubSection;
    private WhenCreateWithoutUnity whenCreateWithoutUnity;
    private CalculateMargin calculateMargin;
    private GenerateInternalCode generateInternalCode;

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
        if (whenCreateWithoutSubSection.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui uma subseção cadastrada");
        }
        if (whenCreateWithoutProvider.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui um fornecedor cadastrado");
        }
        if (whenCreateWithOutName.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem o nome.");
        }
        if (whenCreateWithoutUnity.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem o tipo de unidade de medida.");
        }
        BigDecimal margin = calculateMargin.calculate(productData);
        BigInteger incrementInternalCode = generateInternalCode.generate(productData);
        productData.setMargin(margin);
        productData.setInternalCode(incrementInternalCode);
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
