package br.com.amorim.supermarket.service.otherdiscount;

import br.com.amorim.supermarket.model.otherdiscount.OtherDiscount;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OtherDiscountCrudService {

    Page<OtherDiscount> getAll(int page, int size);
    OtherDiscount findById(UUID id);
    OtherDiscount save (OtherDiscount otherDiscount);
    void update (OtherDiscount otherDiscount, UUID id);
    void delete (UUID id);
}
