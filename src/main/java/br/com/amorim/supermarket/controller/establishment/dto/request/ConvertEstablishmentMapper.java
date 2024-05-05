package br.com.amorim.supermarket.controller.establishment.dto.request;

import br.com.amorim.supermarket.model.establishment.Establishment;

public interface ConvertEstablishmentMapper {

    Establishment createOrUpdateEstablishmentMapper(EstablishmentDTO establishmentDTO);
}
