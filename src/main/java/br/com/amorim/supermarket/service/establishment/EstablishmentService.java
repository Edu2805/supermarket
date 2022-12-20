package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablisumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EstablishmentService {

    EstablisumentRepository establisumentRepository;

    public List<Establishment> findBuId () {
        return establisumentRepository.findAll();
    }

    public Establishment findById (UUID id) {
        return establisumentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Loja não encontrado");
                });
    }
    @Transactional
    public Establishment save (Establishment establishment) {
        return establisumentRepository.save(establishment);
    }

    @Transactional
    public void update (Establishment establishment, UUID id) {
        establisumentRepository.findById(id)
                .map(existingEstablishment -> {
                    establishment.setId(existingEstablishment.getId());
                    establisumentRepository.save(establishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Loja não encontrado"));
    }

    @Transactional
    public void delete (UUID id) {
        establisumentRepository.findById(id)
                .map(existingEstablishment -> {
                    establisumentRepository.delete(existingEstablishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Loja não encontrado"));
    }
}
