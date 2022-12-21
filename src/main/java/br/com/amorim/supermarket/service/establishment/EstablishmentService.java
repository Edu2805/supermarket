package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor

@Service
public class EstablishmentService {

    EstablishmentRepository establishmentRepository;

    public List<Establishment> getAll () {
        return establishmentRepository.findAll();
    }

    public Establishment findById (UUID id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Loja não encontrado");
                });
    }
    @Transactional
    public Establishment save (Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    @Transactional
    public void update (Establishment establishment, UUID id) {
        establishmentRepository.findById(id)
                .map(existingEstablishment -> {
                    establishment.setId(existingEstablishment.getId());
                    establishmentRepository.save(establishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Loja não encontrado"));
    }

    @Transactional
    public void delete (UUID id) {
        establishmentRepository.findById(id)
                .map(existingEstablishment -> {
                    establishmentRepository.delete(existingEstablishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Loja não encontrado"));
    }
}
