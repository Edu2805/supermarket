package br.com.amorim.supermarket.service.subsection;

import br.com.amorim.supermarket.model.subsection.SubSection;
import br.com.amorim.supermarket.repository.subsection.SubSectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class SubSectionService {

    SubSectionRepository subSectionRepository;

    public List<SubSection> getAll () {
        return subSectionRepository.findAll();
    }

    public SubSection findById (UUID id) {
        return subSectionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Sub-seção não encontrada");
                });
    }

    @Transactional
    public SubSection save (SubSection subSection) {
        return subSectionRepository.save(subSection);
    }

    @Transactional
    public void update (SubSection subSection, UUID id) {
        subSectionRepository.findById(id)
                .map(existingSubSection -> {
                    subSection.setId(existingSubSection.getId());
                    subSectionRepository.save(subSection);
                    return existingSubSection;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Sub-seção não encontrada"));
    }

    @Transactional
    public void delete (UUID id) {
        subSectionRepository.findById(id)
                .map(existingSubSection -> {
                    subSectionRepository.delete(existingSubSection);
                    return existingSubSection;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Sub-seção não encontrada"));
    }
}
