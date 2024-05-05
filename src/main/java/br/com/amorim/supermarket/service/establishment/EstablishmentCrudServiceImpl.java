package br.com.amorim.supermarket.service.establishment;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.model.attatchment.Attachment;
import br.com.amorim.supermarket.model.establishment.Establishment;
import br.com.amorim.supermarket.repository.attachment.AttachmentRepository;
import br.com.amorim.supermarket.repository.establishment.EstablishmentRepository;
import br.com.amorim.supermarket.service.common.utils.ImageUtil;
import br.com.amorim.supermarket.service.establishment.generateinternalcode.GenerateInternalCodeEstablishment;
import br.com.amorim.supermarket.service.establishment.verifycnpjestablishment.VerifyCnpjEstablishment;
import br.com.amorim.supermarket.service.establishment.verifydepartment.VerifyDepartment;
import br.com.amorim.supermarket.service.establishment.verifymunicipalorstateregistration.VerifyMunicipalOrStateRegistrationEstablishment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.UUID;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Service
public class EstablishmentCrudServiceImpl implements EstablishmentCrudService {

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private EstablishmentRepository establishmentRepository;
    private GenerateInternalCodeEstablishment generateInternalCodeEstablishment;
    private VerifyMunicipalOrStateRegistrationEstablishment verifyMunicipalRegistration;
    private VerifyCnpjEstablishment verifyCnpjEstablishment;
    private VerifyPageSize verifyPageSize;
    private VerifyDepartment verifyDepartment;
    private AttachmentRepository attachmentRepository;

    @Override
    public Page<Establishment> getAll (int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return establishmentRepository.findAll(pageableRequest);
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
        setPhotoAndInsert(establishment);
        return establishmentRepository.save(establishment);
    }

    private void setPhotoAndInsert(Establishment establishment) {
        if (establishment.getEstablismentLogo() != null) {
            var imageData = establishment.getEstablismentLogo().getImageData();
            establishment.getEstablismentLogo().setImageData(ImageUtil.compressFile(imageData));
            attachmentRepository.save(establishment.getEstablismentLogo());
        }
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
                    setPhotoAndUpdate(establishment, existingEstablishment);
                    establishmentRepository.save(establishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message)));
    }

    private void setPhotoAndUpdate(Establishment establishmentUpdateLogo, Establishment establishmentExistentLogo) {
        if (establishmentExistentLogo.getEstablismentLogo() == null) {
            var imageData = establishmentUpdateLogo.getEstablismentLogo().getImageData();
            establishmentUpdateLogo.getEstablismentLogo().setImageData(ImageUtil.compressFile(imageData));
            attachmentRepository.save(establishmentUpdateLogo.getEstablismentLogo());
        } else {
            if (!establishmentExistentLogo.getEstablismentLogo().getId().equals(establishmentUpdateLogo.getEstablismentLogo().getId())) {
                deletePhoto(establishmentExistentLogo.getEstablismentLogo());
                var imageData = establishmentUpdateLogo.getEstablismentLogo().getImageData();
                establishmentUpdateLogo.getEstablismentLogo().setImageData(ImageUtil.compressFile(imageData));
                attachmentRepository.save(establishmentUpdateLogo.getEstablismentLogo());
            }
        }
    }

    private void deletePhoto(Attachment attachment) {
        if (attachment != null) {
            attachmentRepository.delete(attachment);
        }
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
                    verifyDepartment.verifyEstablishmentDepartment(existingEstablishment);
                    establishmentRepository.delete(existingEstablishment);
                    return existingEstablishment;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.ESTABLISHMENT_NOT_FOUND.message)));
    }
}
