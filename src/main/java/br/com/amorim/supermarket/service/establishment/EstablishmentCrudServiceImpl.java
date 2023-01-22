package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.service.establishment.generateinternalcode.GenerateInternalCodeEstablishment;
import br.com.amorim.supermarket.service.establishment.verifycnpjestablishment.VerifyCnpjEstablishment;
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
public class EstablishmentCrudServiceImpl implements EstablishmentCrudService {

    private EstablishmentRepository establishmentRepository;
    private GenerateInternalCodeEstablishment generateInternalCodeEstablishment;
    private VerifyMunicipalOrStateRegistrationEstablishment verifyMunicipalRegistration;
    private VerifyCnpjEstablishment verifyCnpjEstablishment;

    @Override
    public List<Establishment> getAll () {
        return establishmentRepository.findAll();
    }

    @Override
    public Establishment findById (UUID id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message));
                });
    }
    @Transactional
    @Override
    public Establishment save (Establishment establishment) {
        validateFieldsBeforeSave(establishment);
        setInternalCode(establishment);
        return establishmentRepository.save(establishment);
    }

    private void setInternalCode (Establishment establishment) {
        BigInteger incrementInternalCode = generateInternalCodeEstablishment.generate(establishment);
        establishment.setCode(incrementInternalCode);
    }

    private void validateFieldsBeforeSave(Establishment establishment) {
        verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeSave(establishment);
        verifyMunicipalRegistration.verifyMunicipalOrStateRegistrationBeforeSave(establishment);
    }

    @Transactional
    @Override
    public void update (Establishment establishment, UUID id) {
        establishmentRepository.findById(id)
                .map(existingEstablishment -> {
                    establishment.setId(existingEstablishment.getId());
                    validateFieldsBeforeUpdate(establishment);
                    establishment.setCode(existingEstablishment.getCode());
                    establishmentRepository.save(establishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message)));
    }

    private void validateFieldsBeforeUpdate(Establishment establishment) {
        verifyCnpjEstablishment.verifyCnpjEstablishmentBeforeUpdate(establishment);
        verifyMunicipalRegistration.verifyMunicipalOrStateRegistrationBeforeUpdate(establishment);
    }

    @Transactional
    @Override
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
