package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.model.establishment.Establishment;

import java.util.List;
import java.util.UUID;

public interface EstablishmentCrudService {

    List<Establishment> getAll();
    Establishment findById(UUID id);
    Establishment save (Establishment establishment);
    void update (Establishment establishment, UUID id);
    void delete (UUID id);
}
