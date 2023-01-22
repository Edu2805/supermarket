package br.com.amorim.supermarket.service.establishment.validatecnpj;

import br.com.amorim.supermarket.model.establishment.Establishment;

public interface ValidateCnpjDocumentEstablishment {

    boolean verifySubscriptionNumber (Establishment establishment);
}
