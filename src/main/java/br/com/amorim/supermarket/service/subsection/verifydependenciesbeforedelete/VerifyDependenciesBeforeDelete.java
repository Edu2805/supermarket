package br.com.amorim.supermarket.service.subsection.verifydependenciesbeforedelete;

import br.com.amorim.supermarket.model.subsection.SubSection;

public interface VerifyDependenciesBeforeDelete {

    void existsProductInSubSection(SubSection subSection);
    void existsEmployeeInSubSection(SubSection subSection);
}
