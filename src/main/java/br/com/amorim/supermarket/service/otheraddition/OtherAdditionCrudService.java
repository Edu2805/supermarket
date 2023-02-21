package br.com.amorim.supermarket.service.otheraddition;

import br.com.amorim.supermarket.model.otheraddition.OtherAddition;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OtherAdditionCrudService {

    Page<OtherAddition> getAll(int page, int size);
    OtherAddition findById(UUID id);
    OtherAddition save (OtherAddition otherAddition);
    void update (OtherAddition otherAddition, UUID id);
    void delete (UUID id);
}
