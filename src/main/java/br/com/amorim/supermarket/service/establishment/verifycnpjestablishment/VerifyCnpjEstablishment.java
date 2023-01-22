package br.com.amorim.supermarket.service.establishment.verifycnpjestablishment;

import br.com.amorim.supermarket.model.establishment.Establishment;

public interface VerifyCnpjEstablishment {

    boolean verifyCnpjEstablishmentBeforeSave(Establishment establishment);
    boolean verifyCnpjEstablishmentBeforeUpdate(Establishment establishment);
}
