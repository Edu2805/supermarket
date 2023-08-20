package br.com.amorim.supermarket.controller.person.dto.mapper;

import br.com.amorim.supermarket.controller.person.dto.request.PersonDTO;
import br.com.amorim.supermarket.controller.person.dto.request.PersonScholarityStringDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ScholarityForPersonMapperImpl implements ScholarityForPersonMapper {

    private ScholarityTypeMapper scholarityTypeMapper;

    @Override
    public PersonDTO personMapper(PersonScholarityStringDTO personScholarityStringDTO) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setFirstName(personScholarityStringDTO.getFirstName());
        personDTO.setMiddleName(personScholarityStringDTO.getMiddleName());
        personDTO.setLastName(personScholarityStringDTO.getLastName());
        personDTO.setCpf(personScholarityStringDTO.getCpf());
        personDTO.setRg(personScholarityStringDTO.getRg());
        personDTO.setNationality(personScholarityStringDTO.getNationality());
        personDTO.setNaturalness(personScholarityStringDTO.getNaturalness());
        personDTO.setBirthDate(personScholarityStringDTO.getBirthDate());
        personDTO.setScholarity(scholarityTypeMapper.mapperScholarityType(personScholarityStringDTO.getScholarity()));
        personDTO.setDependents(personScholarityStringDTO.getDependents());
        personDTO.setFatherName(personScholarityStringDTO.getFatherName());
        personDTO.setMotherName(personScholarityStringDTO.getMotherName());
        personDTO.setUserData(personScholarityStringDTO.getUserData());
        personDTO.setPersonPhoto(personScholarityStringDTO.getPersonPhoto());
        return personDTO;
    }
}
