package br.com.amorim.supermarket.service.mainsection.verifymainsectionname;

import br.com.amorim.supermarket.model.mainsection.MainSection;

public interface VerifyMainSectionName {

    boolean existsByMainSectionNameBeforeSave(MainSection mainSection);
    boolean existsByMainSectionNameBeforeUpdate(MainSection mainSection);
}
