package br.com.amorim.supermarket.controller.person.dto.mapper;

import br.com.amorim.supermarket.common.enums.ScholarityType;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.common.enums.ScholarityType.DOCTORATE_DEGREE;
import static br.com.amorim.supermarket.common.enums.ScholarityType.ELEMENTARY_SCHOOL;
import static br.com.amorim.supermarket.common.enums.ScholarityType.HIGH_SCHOOL;
import static br.com.amorim.supermarket.common.enums.ScholarityType.ILLITERATE;
import static br.com.amorim.supermarket.common.enums.ScholarityType.MASTER_DEGREE;
import static br.com.amorim.supermarket.common.enums.ScholarityType.POSTGRADUATE;
import static br.com.amorim.supermarket.common.enums.ScholarityType.UNINFORMED;
import static br.com.amorim.supermarket.common.enums.ScholarityType.UNIVERSITY_EDUCATION;
import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@Component
public class ScholarityTypeMapperImpl implements ScholarityTypeMapper {

    @Override
    public ScholarityType mapperScholarityType(String scholarity) {
        ScholarityType scholarityType = UNINFORMED;
        if (getString(ILLITERATE.name()).equals(scholarity)) {
            scholarityType = ILLITERATE;
        } else if (getString(ELEMENTARY_SCHOOL.name()).equals(scholarity)) {
            scholarityType = ELEMENTARY_SCHOOL;
        }
        else if (getString(HIGH_SCHOOL.name()).equals(scholarity)) {
            scholarityType = HIGH_SCHOOL;
        }
        else if (getString(UNIVERSITY_EDUCATION.name()).equals(scholarity)) {
            scholarityType = UNIVERSITY_EDUCATION;
        }
        else if (getString(POSTGRADUATE.name()).equals(scholarity)) {
            scholarityType = POSTGRADUATE;
        }
        else if (getString(MASTER_DEGREE.name()).equals(scholarity)) {
            scholarityType = MASTER_DEGREE;
        }
        else if (getString(DOCTORATE_DEGREE.name()).equals(scholarity)) {
            scholarityType = DOCTORATE_DEGREE;
        }
        return scholarityType;
    }
}
