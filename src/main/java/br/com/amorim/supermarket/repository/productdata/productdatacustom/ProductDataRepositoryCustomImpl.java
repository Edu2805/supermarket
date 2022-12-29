package br.com.amorim.supermarket.repository.productdata.productdatacustom;

import br.com.amorim.supermarket.model.productdata.ProductData;
import org.springframework.stereotype.Component;

@Component
public class ProductDataRepositoryCustomImpl implements ProductDataRepositoryCustom {

    @Override
    public boolean existsByInternalCode(ProductData productData) {
        return false;
    }
}
