package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;
import br.com.amorim.supermarket.service.productdata.productvalidator.ProductValidatorsImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class ProductDataService {

    ProductDataReposotiry productDataReposotiry;
    ProductValidatorsImpl productValidators;

    public List<ProductData> getAll() {
        return productDataReposotiry.findAll();
    }

    public ProductData findById(UUID id) {
        return productDataReposotiry.findById(id)
                .orElseThrow(() -> {
                   throw new ResponseStatusException(NOT_FOUND,
                           "Produto não encontrado");
                });
    }

    @Transactional
    public ProductData save (ProductData productData) {
        // todo Criar um Handler de erros
        if (!productValidators.whenCreateWithOutProviderAndSubSection(productData)) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Houve um erro ao cadastrar o produto, verifique se o mesmo já possui uma subseção e um fornecedor cadastrado");
        }
        if (productValidators.whenCreateWithOutName(productData.getName())) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "Não é possível cadastrar um produto sem o nome.");
        }
        return productDataReposotiry.save(productData);
    }

    @Transactional
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
