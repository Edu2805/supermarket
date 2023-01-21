package br.com.amorim.supermarket.common.enums;

public enum MessagesKeyType {

    PRODUCT_DATA_EAN13_OR_DUN14_EMPTY ("PRODUCT_DATA_EAN13_OR_DUN14_EMPTY"),
    PRODUCT_DATA_EAN13_OR_DUN14_SAVE_TOGETHER ("PRODUCT_DATA_EAN13_OR_DUN14_SAVE_TOGETHER"),
    PRODUCT_DATA_EAN13_ALREADY_EXISTS ("PRODUCT_DATA_EAN13_ALREADY_EXISTS"),
    PRODUCT_DATA_DUN14_ALREADY_EXISTS ("PRODUCT_DATA_DUN14_ALREADY_EXISTS"),
    PRODUCT_DATA_SUB_SECTION_NON_EXISTENT ("PRODUCT_DATA_SUB_SECTION_NON_EXISTENT"),
    PRODUCT_DATA_PROVIDER_PRODUCT_NON_EXISTENT ("PRODUCT_DATA_PROVIDER_PRODUCT_NON_EXISTENT"),
    PRODUCT_DATA_NOT_FOUND ("PRODUCT_DATA_NOT_FOUND"),
    PROVIDER_PRODUCT_NOT_FOUND ("PROVIDER_PRODUCT_NOT_FOUND"),
    PROVIDER_PRODUCT_MUNICIPAL_REGISTER_ALREADY_EXISTS ("PROVIDER_PRODUCT_MUNICIPAL_REGISTER_ALREADY_EXISTS"),
    PROVIDER_PRODUCT_STATE_REGISTER_ALREADY_EXISTS ("PROVIDER_PRODUCT_STATE_REGISTER_ALREADY_EXISTS"),
    PROVIDER_PRODUCT_SUBSCRIPTION_NUMBER_ALREADY_EXISTS ("PROVIDER_PRODUCT_SUBSCRIPTION_NUMBER_ALREADY_EXISTS"),
    PROVIDER_PRODUCT_GENERIC_SUBSCRIPTION_NUMBER_NOT_EXISTS ("PROVIDER_PRODUCT_GENERIC_SUBSCRIPTION_NUMBER_NOT_EXISTS"),
    PROVIDER_PRODUCT_INCORRECT_CPF_NUMBER ("PROVIDER_PRODUCT_INCORRECT_CPF_NUMBER"),
    PROVIDER_PRODUCT_INCORRECT_CNPJ_NUMBER ("PROVIDER_PRODUCT_INCORRECT_CNPJ_NUMBER"),
    COMMON_PAGE_SIZE_INVALID_PAGE_SIZE ("COMMON_PAGE_SIZE_INVALID_PAGE_SIZE");

    public final String message;

    MessagesKeyType(String message) {
        this.message = message;
    }
}
