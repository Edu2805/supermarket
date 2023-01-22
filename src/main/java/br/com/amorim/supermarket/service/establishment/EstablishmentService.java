package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.repository.establishment.verifycnpjrepositorycustom.VerifyCnpjEstablishmentRepositoryCustom;
import br.com.amorim.supermarket.service.establishment.generateinternalcode.GenerateInternalCodeEstablishment;
import br.com.amorim.supermarket.service.establishment.verifymanagerestablishment.VerifyManagerEstablishment;
import br.com.amorim.supermarket.service.establishment.verifymunicipalorstateregistration.VerifyMunicipalOrStateRegistrationEstablishment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class EstablishmentService {

    private EstablishmentRepository establishmentRepository;
    private GenerateInternalCodeEstablishment generateInternalCodeEstablishment;
    private VerifyMunicipalOrStateRegistrationEstablishment verifyMunicipalRegistration;
    private VerifyCnpjEstablishmentRepositoryCustom verifySubscriptionNumber;
    private VerifyManagerEstablishment verifyManagerEstablishment;

    public List<Establishment> getAll () {
        return establishmentRepository.findAll();
    }

    public Establishment findById (UUID id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message));
                });
    }
    @Transactional
    public Establishment save (Establishment establishment) {
        validateFields(establishment);
        setInternalCode(establishment);
        return establishmentRepository.save(establishment);
    }

    private void setInternalCode (Establishment establishment) {
        BigInteger incrementInternalCode = generateInternalCodeEstablishment.generate(establishment);
        establishment.setCode(incrementInternalCode);
    }

    private void validateFields (Establishment establishment) {
        verifySubscriptionNumber.existsByCnpj(establishment);
        verifyMunicipalRegistration.verifyMunicipalOrStateRegistration(establishment);
        verifyManagerEstablishment.verifyManagerEstablishmentRegistred(establishment);
    }

    @Transactional
    public void update (Establishment establishment, UUID id) {
        establishmentRepository.findById(id)
                .map(existingEstablishment -> {
                    establishment.setId(existingEstablishment.getId());
                    validateFields(existingEstablishment);
                    establishment.setCode(existingEstablishment.getCode());
                    establishmentRepository.save(establishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message)));
    }

    @Transactional
    public void delete (UUID id) {
        establishmentRepository.findById(id)
                .map(existingEstablishment -> {
                    establishmentRepository.delete(existingEstablishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message)));
    }
}
