package br.com.amorim.supermarket.repository.subsection;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.model.subsection.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubSectionRepository extends JpaRepository<SubSection, UUID> {

    boolean existsByName(String name);
    boolean existsByMainSection(MainSection mainSection);
}
