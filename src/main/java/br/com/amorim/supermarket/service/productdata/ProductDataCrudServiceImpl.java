package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMargin;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCode;
import br.com.amorim.supermarket.service.productdata.productvalidator.ProductValitador;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier(value = "validateName") @NonNull private ProductValitador validateName;
    @Qualifier(value = "validateProvider") @NonNull private ProductValitador validateProvider;
    @Qualifier(value = "validateSubsection") @NonNull private ProductValitador validateSubsection;
    @Qualifier(value = "validateUnity") @NonNull private ProductValitador validateUnity;
    @Qualifier(value = "validateEAN13OrDUN14") @NonNull private ProductValitador validateEAN13OrDUN14;
    @Qualifier(value = "validateInventory") @NonNull private ProductValitador validateInventory;
    @Qualifier(value = "validatePurchasePrice") @NonNull private ProductValitador validatePurchasePrice;
    @Qualifier(value = "validateSalePrice") @NonNull private ProductValitador validateSalePrice;
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
        // todo Tentar isolar as mensagens em um método
        if (validateSubsection.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui uma subseção cadastrada");
        }
        if (validateProvider.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui um fornecedor cadastrado");
        }
        if (validateName.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem o nome.");
        }
        if (validateUnity.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem o tipo de unidade de medida.");
        }
        if (validateEAN13OrDUN14.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem um EAN 13 ou um DUN 14.");
        }
        if (validateInventory.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto com estoque nulo.");
        }
        if (validatePurchasePrice.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem preço de compra.");
        }
        if (validateSalePrice.validate(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem preço de venda.");
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
