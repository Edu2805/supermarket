package br.com.amorim.supermarket.service.productdata.validateean13anddun14;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.businessrule.BusinessRuleException;
import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.repository.productdata.ProductDataRepository;
import br.com.amorim.supermarket.repository.productdata.verifyean13ordun14repositorycustom.VerifyEan13OrDun14RepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component("validateEAN13OrDUN14")
public class ValidateProductEan13OrDun14Impl implements ValidateProductEan13OrDun14 {

    private VerifyEan13OrDun14RepositoryCustom verifyEan13OrDun14RepositoryCustom;
    private ProductDataRepository productDataRepository;

    /**
     * Um produto deve ter ou um EAN 13 ou um DUN 14
     * @param productData produto cadastrado
     * @return retorna true caso o EAN 13 ou DUN 14 for igual a null junto da mensagem de orientação ao usuário
     */
    @Override
    public boolean validateBeforeSaveAndUpdate(ProductData productData) {
        if (productData.getEan13() == null && productData.getDun14() == null) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_EMPTY.message));
        } else if (productData.getEan13() != null && productData.getDun14() != null) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_SAVE_TOGETHER.message));
        } else {
            verifyIfAlreadyExistsEan13OrDun14InDatabase(productData);
        }
        return false;
    }

    private void verifyIfAlreadyExistsEan13OrDun14InDatabase(ProductData productData) {
        if(verifyEan13OrDun14RepositoryCustom.existsByEan13OrDun14(productData) == 1) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_EAN13_ALREADY_EXISTS.message));
        }
        if (verifyEan13OrDun14RepositoryCustom.existsByEan13OrDun14(productData) == 2) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_DUN14_ALREADY_EXISTS.message));
        }
    }

    @Override
    public boolean validateBeforeUpdate(ProductData productData) {
        if (productData.getEan13() == null && productData.getDun14() == null) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_EMPTY.message));
        } else if (productData.getEan13() != null && productData.getDun14() != null) {
            throw new BusinessRuleException(getString(MessagesKeyType.PRODUCT_DATA_EAN13_OR_DUN14_SAVE_TOGETHER.message));
        } else {
            validateEan13(productData);
        }
        return false;
    }

    private void validateEan13 (ProductData productData) {
        var getProducts = productDataRepository.findAll();
        getProducts.forEach(product -> {
            if (product.getEan13() != null && (product.getEan13().equals(productData.getEan13()))) {
                if (!product.getId().equals(productData.getId())) {
                    throw new BusinessRuleException(
                            getString(MessagesKeyType.PRODUCT_DATA_EAN13_ALREADY_EXISTS.message));
                }
            }
            if (product.getDun14() != null && (product.getDun14().equals(productData.getDun14()))) {
                if (!product.getId().equals(productData.getId())) {
                    throw new BusinessRuleException(
                            getString(MessagesKeyType.PRODUCT_DATA_DUN14_ALREADY_EXISTS.message));
                }
            }
        });
    }
}
