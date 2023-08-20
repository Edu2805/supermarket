package br.com.amorim.supermarket.common.enums;

public enum ScholarityType {

    ILLITERATE("{br.com.supermarket.ILLITERATE}"),
    ELEMENTARY_SCHOOL("{br.com.supermarket.ELEMENTARY_SCHOOL}"),
    HIGH_SCHOOL("{br.com.supermarket.HIGH_SCHOOL}"),
    UNIVERSITY_EDUCATION("{br.com.supermarket.UNIVERSITY_EDUCATION}"),
    POSTGRADUATE("{messages_es.properties}"),
    MASTER_DEGREE("{br.com.supermarket.MASTER_DEGREE}"),
    DOCTORATE_DEGREE("{br.com.supermarket.DOCTORATE_DEGREE}"),
    UNINFORMED("{br.com.supermarket.UNINFORMED}");

    public final String scholarity;

    ScholarityType(String scholarity) {
        this.scholarity = scholarity;
    }
}
