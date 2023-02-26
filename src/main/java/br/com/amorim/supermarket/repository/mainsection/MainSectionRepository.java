package br.com.amorim.supermarket.repository.mainsection;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MainSectionRepository extends JpaRepository<MainSection, UUID> {

    boolean existsByName(String name);
}
