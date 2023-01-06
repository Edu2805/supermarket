package br.com.amorim.supermarket.repository.productdata.verifyean13ordun14;

import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataReposotiry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class VerifyEan13OrDun14RepositoryCustomImpl implements VerifyEan13OrDun14RepositoryCustom{

    private ProductDataReposotiry productDataReposotiry;

    @Override
    public void existsByEan13OrDun14(ProductData productData) {
        if (productDataReposotiry.existsByEan13(productData.getEan13())) {
            throw new BusinessRuleException(
                        "Já existe um produto cadastrado com o mesmo EAN 13.");
        }
        if (productDataReposotiry.existsByDun14(productData.getDun14())) {
            throw new BusinessRuleException(
                    "Já existe um produto cadastrado com o mesmo DUN 14.");
        }
    }
}
