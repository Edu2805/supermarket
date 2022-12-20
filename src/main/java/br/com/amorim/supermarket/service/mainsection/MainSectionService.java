package br.com.amorim.supermarket.service.mainsection;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import br.com.amorim.supermarket.repository.mainsection.MainSectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MainSectionService {

    MainSectionRepository mainSectionRepository;

    public List<MainSection> getAll () {
        return mainSectionRepository.findAll();
    }

    public MainSection findById (UUID id) {
        return mainSectionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Seção principal não encontrada");
                });
    }

    @Transactional
    public MainSection save (MainSection mainSection) {
        return mainSectionRepository.save(mainSection);
    }

    @Transactional
    public void update (MainSection mainSection, UUID id) {
        mainSectionRepository.findById(id)
                .map(existingMainSection -> {
                    mainSection.setId(existingMainSection.getId());
                    mainSectionRepository.save(mainSection);
                    return existingMainSection;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Seção principal não encontrada"));
    }

    @Transactional
    public void delete (UUID id) {
        mainSectionRepository.findById(id)
                .map(existingMainSection -> {
                    mainSectionRepository.delete(existingMainSection);
                    return existingMainSection;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Seção principal não encontrada"));
    }
}
