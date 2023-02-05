package br.com.amorim.supermarket.service.providerproduct;

import br.com.amorim.supermarket.common.enums.MessagesKeyType;
import br.com.amorim.supermarket.common.exception.invaliddocument.InvalidDocumentException;
import br.com.amorim.supermarket.common.exception.notfound.NotFoundException;
import br.com.amorim.supermarket.model.providerproduct.ProviderProduct;
import br.com.amorim.supermarket.repository.providerproduct.ProviderProductRepository;
import br.com.amorim.supermarket.service.providerproduct.generateinternalcode.GenerateInternalCodeProviderProduct;
import br.com.amorim.supermarket.service.providerproduct.validatedocument.cei.ValidateCeiDocumentProviderProduct;
import br.com.amorim.supermarket.service.providerproduct.validatedocument.cnpj.ValidateCnpjDocumentProviderProduct;
import br.com.amorim.supermarket.service.providerproduct.validatedocument.cpf.ValidateCpfDocumentProviderProduct;
import br.com.amorim.supermarket.service.providerproduct.verifymunicipalorstateregistration.VerifyMunicipalOrStateRegistrationProviderProduct;
import br.com.amorim.supermarket.common.verifypagesize.VerifyPageSize;
import br.com.amorim.supermarket.service.providerproduct.verifysubscriptionnumber.VerifySubscriptionNumberProviderProduct;
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
public class ProviderProductCrudServiceImpl implements ProviderProductCrudService{

    private static final int DECREASE_PAGE_SIZE = 1;
    private static final int ZERO_PAGE_SIZE = 0;
    private ProviderProductRepository providerProductRepository;
    private GenerateInternalCodeProviderProduct generateInternalCodeProviderProduct;
    private ValidateCnpjDocumentProviderProduct validateCnpjDocument;
    private ValidateCpfDocumentProviderProduct validateCpfDocument;
    private ValidateCeiDocumentProviderProduct validateCeiDocument;
    private VerifyMunicipalOrStateRegistrationProviderProduct verifyMunicipalRegistration;
    private VerifySubscriptionNumberProviderProduct verifySubscriptionNumber;
    private VerifyPageSize verifyPageSize;


    @Override
    public Page<ProviderProduct> getAll(int page, int size) {
        if (page > ZERO_PAGE_SIZE) {
            page -= DECREASE_PAGE_SIZE;
        }
        verifyPageSize.verifyPageSizeForGetAll(page, size);
        Pageable pageableRequest = PageRequest.of(page, size);
        return providerProductRepository.findAll(pageableRequest);
    }

    @Override
    public ProviderProduct findById(UUID id) {
        return providerProductRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            getString(MessagesKeyType.PROVIDER_PRODUCT_NOT_FOUND.message));
                });
    }

    @Transactional
    @Override
    public ProviderProduct save (ProviderProduct providerProduct) {
        validateDocumentsBeforeSave(providerProduct);
        validateFieldsBeforeSave(providerProduct);
        setInternalCode(providerProduct);
        return providerProductRepository.save(providerProduct);
    }

    private void validateDocumentsBeforeSave(ProviderProduct providerProduct) {
        switch (providerProduct.getSubscriptionType()) {
            case CPF -> validateCpfDocument.isCpf(providerProduct);
            case CNPJ -> validateCnpjDocument.isCnpj(providerProduct);
            case CEI -> validateCeiDocument.isCei(providerProduct);
            default -> throw new InvalidDocumentException(
                    getString(MessagesKeyType.PROVIDER_PRODUCT_GENERIC_SUBSCRIPTION_NUMBER_NOT_EXISTS.message));
        }
    }

    private void validateFieldsBeforeSave(ProviderProduct providerProduct) {
        verifySubscriptionNumber.verifySubscriptionNumberBeforeSave(providerProduct);
        verifyMunicipalRegistration.verifyMunicipalOrStateRegistrationBeforeSave(providerProduct);
    }

    private void setInternalCode (ProviderProduct providerProduct) {
        BigInteger incrementInternalCode = generateInternalCodeProviderProduct.generate(providerProduct);
        providerProduct.setCode(incrementInternalCode);
    }

    @Transactional
    @Override
    public void update (ProviderProduct providerProduct, UUID id) {
        providerProductRepository.findById(id)
                .map(existingProvider -> {
                    providerProduct.setId(existingProvider.getId());
                    validateDocumentsBeforeUpdate(providerProduct);
                    validateFieldsBeforeUpdate(providerProduct);
                    providerProduct.setCode(existingProvider.getCode());
                    providerProductRepository.save(providerProduct);
                    return existingProvider;
                }).orElseThrow(() ->
                         new NotFoundException(
                                 getString(MessagesKeyType.PROVIDER_PRODUCT_NOT_FOUND.message)));

    }

    private void validateDocumentsBeforeUpdate(ProviderProduct providerProduct) {
        switch (providerProduct.getSubscriptionType()) {
            case CPF -> validateCpfDocument.isCpf(providerProduct);
            case CNPJ -> validateCnpjDocument.isCnpj(providerProduct);
            case CEI -> validateCeiDocument.isCei(providerProduct);
            default -> throw new InvalidDocumentException(
                    getString(MessagesKeyType.PROVIDER_PRODUCT_GENERIC_SUBSCRIPTION_NUMBER_NOT_EXISTS.message));
        }
    }

    private void validateFieldsBeforeUpdate(ProviderProduct providerProduct) {
        verifySubscriptionNumber.verifySubscriptionNumberBeforeUpdate(providerProduct);
        verifyMunicipalRegistration.verifyMunicipalOrStateRegistrationBeforeUpdate(providerProduct);
    }

    @Transactional
    @Override
    public void delete (UUID id) {
        providerProductRepository.findById(id)
                .map(provider -> {
                    providerProductRepository.delete(provider);
                    return provider;
                }).orElseThrow(() ->
                        new NotFoundException(
                                getString(MessagesKeyType.PROVIDER_PRODUCT_NOT_FOUND.message)));
    }
}
