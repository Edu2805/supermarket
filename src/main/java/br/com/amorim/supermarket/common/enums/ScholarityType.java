package br.com.amorim.supermarket.common.enums;

public enum ScholarityType {

    ILLITERATE("ILLITERATE"),
    ELEMENTARY_SCHOOL("ELEMENTARY_SCHOOL"),
    HIGH_SCHOOL("HIGH_SCHOOL"),
    UNIVERSITY_EDUCATION("UNIVERSITY_EDUCATION"),
    POSTGRADUATE("POSTGRADUATE"),
    MASTER_DEGREE("MASTER_DEGREE"),
    DOCTORATE_DEGREE("DOCTORATE_DEGREE"),
    UNINFORMED("UNINFORMED");

    public final String scholarity;

    ScholarityType(String scholarity) {
        this.scholarity = scholarity;
    }
}
