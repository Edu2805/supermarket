package br.com.amorim.supermarket.repository.productdata.verifyean13ordun14;

import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class VerifyEan13OrDun14RepositoryCustomImpl implements VerifyEan13OrDun14RepositoryCustom{

    private ProductDataRepository productDataRepository;

    @Override
    public boolean existsByEan13OrDun14(ProductData productData) {
        if (productDataRepository.existsByEan13(productData.getEan13())) {
            throw new BusinessRuleException(
                        "Já existe um produto cadastrado com o mesmo EAN 13.");
        }
        if (productDataRepository.existsByDun14(productData.getDun14())) {
            throw new BusinessRuleException(
                    "Já existe um produto cadastrado com o mesmo DUN 14.");
        }
        return false;
    }
}
