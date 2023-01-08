package br.com.amorim.supermarket.service.productdata.productvalidator;

import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom.VerifyEan13OrDun14RepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component("validateEAN13OrDUN14")
public class ProductValidatorEan13OrDun14Impl implements ProductValidatorEan13OrDun14 {

    private VerifyEan13OrDun14RepositoryCustom verifyEan13OrDun14RepositoryCustom;

    /**
     * Um produto deve ter ou um EAN 13 ou um DUN 14
     * @param productData produto cadastrado
     * @return retorna true caso o EAN 13 ou DUN 14 for igual a null junto da mensagem de orientação ao usuário
     */
    @Override
    public boolean validate(ProductData productData) {
        if (productData.getEan13() == null && productData.getDun14() == null) {
            throw new BusinessRuleException(
                    "Não é possível cadastrar um produto sem um EAN 13 ou um DUN 14.");
        } else if (productData.getEan13() != null && productData.getDun14() != null) {
            throw new BusinessRuleException(
                    "Não é possível cadastrar um produto com EAN 13 e DUN 14 juntos. Deve haver ou um EAN 13 ou um DUN 14");
        } else {
            verifyEan13OrDun14RepositoryCustom.existsByEan13OrDun14(productData);
        }
        return false;
    }
}
