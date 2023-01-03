package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;
import br.com.amorim.supermarket.service.productdata.calculatemargin.CalculateMarginImpl;
import br.com.amorim.supermarket.service.productdata.generateinternalcode.GenerateInternalCodeImpl;
import br.com.amorim.supermarket.service.productdata.productvalidator.ProductValitadorImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Implementação do CRUD de ProductData
 */

@AllArgsConstructor

@Service
public class ProductDataCrudService implements ProductDataCrudServiceImpl {

    private ProductDataReposotiry productDataReposotiry;
    @Qualifier(value = "validateName") @NonNull private ProductValitadorImpl validateName;
    @Qualifier(value = "validateProvider") @NonNull private ProductValitadorImpl validateProvider;
    @Qualifier(value = "validateSubsection") @NonNull private ProductValitadorImpl validateSubsection;
    @Qualifier(value = "validateUnity") @NonNull private ProductValitadorImpl validateUnity;
    @Qualifier(value = "validateEAN13OrDUN14") @NonNull private ProductValitadorImpl validateEAN13OrDUN14;
    @Qualifier(value = "validateInventory") @NonNull private ProductValitadorImpl validateInventory;
    @Qualifier(value = "validatePurchasePrice") @NonNull private ProductValitadorImpl validatePurchasePrice;
    @Qualifier(value = "validateSalePrice") @NonNull private ProductValitadorImpl validateSalePrice;
    private CalculateMarginImpl calculateMargin;
    private GenerateInternalCodeImpl generateInternalCode;

    @Override
    public List<ProductData> getAll() {
        return productDataReposotiry.findAll();
    }

    @Override
    public ProductData findById(UUID id) {
        return productDataReposotiry.findById(id)
                .orElseThrow(() -> {
                   throw new NotFoundException(
                           "Produto não encontrado");
                });
    }

    @Transactional
    @Override
    public ProductData save (ProductData productData) {
        // todo Criar um Handler de erros
        // todo Tentar isolar as mensagens em um método
        if (validateSubsection.validate(productData)) {
            throw new BusinessRuleException(
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui uma subseção cadastrada");
        }
        if (validateProvider.validate(productData)) {
            throw new BusinessRuleException(
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui um fornecedor cadastrado");
        }
        if (validateName.validate(productData)) {
            throw new BusinessRuleException(
                    "Não é possível cadastrar um produto sem o nome.");
        }
        if (validateUnity.validate(productData)) {
            throw new BusinessRuleException(
                    "Não é possível cadastrar um produto sem o tipo de unidade de medida.");
        }
        if (validateEAN13OrDUN14.validate(productData)) {
            throw new BusinessRuleException(
                    "Não é possível cadastrar um produto sem um EAN 13 ou um DUN 14.");
        }
        if (validateInventory.validate(productData)) {
            throw new BusinessRuleException(
                    "Não é possível cadastrar um produto com estoque nulo.");
        }
        if (validatePurchasePrice.validate(productData)) {
            throw new BusinessRuleException(
                    "Não é possível cadastrar um produto sem preço de compra.");
        }
        if (validateSalePrice.validate(productData)) {
            throw new BusinessRuleException(
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
                                new NotFoundException(
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
                        new NotFoundException(
                                "Produto não encontrado"));
    }
}
