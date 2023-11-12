package br.com.amorim.supermarket.service.productdata;

import br.com.amorim.supermarket.model.productdata.ProductData;
import br.com.amorim.supermarket.service.common.genericcrudservice.CrudServiceCommon;

import java.util.List;
import java.util.UUID;

public interface ProductDataCrudService extends CrudServiceCommon<ProductData, UUID> {
    List<ProductData> filterProducts(ProductData filter);
}
