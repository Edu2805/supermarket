package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.model.establishment.Establishment;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface EstablishmentCrudService {

    Page<Establishment> getAll(int page, int size);
    Establishment findById(UUID id);
    Establishment save (Establishment establishment);
    void update (Establishment establishment, UUID id);
    void delete (UUID id);
}
