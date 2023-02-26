package br.com.amorim.supermarket.service.subsection.verifysubsectionname;

import br.com.amorim.supermarket.model.subsection.SubSection;

public interface VerifySubSectionName {

    boolean existsBySubSectionNameBeforeSave(SubSection subSection);
    boolean existsBySubSectionNameBeforeUpdate(SubSection subSection);
}
