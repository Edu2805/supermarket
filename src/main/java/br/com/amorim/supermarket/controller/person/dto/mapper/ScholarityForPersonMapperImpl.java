package br.com.amorim.supermarket.controller.person.dto.mapper;

import br.com.amorim.supermarket.controller.person.dto.request.PersonScholarityStringDTO;
import br.com.amorim.supermarket.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ScholarityForPersonMapperImpl implements ScholarityForPersonMapper {

    private ScholarityTypeMapper scholarityTypeMapper;

    @Override
    public Person personMapper(PersonScholarityStringDTO personScholarityStringDTO) {
        Person person = new Person();
        person.setFirstName(personScholarityStringDTO.getFirstName());
        person.setMiddleName(personScholarityStringDTO.getMiddleName());
        person.setLastName(personScholarityStringDTO.getLastName());
        person.setCpf(personScholarityStringDTO.getCpf());
        person.setRg(personScholarityStringDTO.getRg());
        person.setNationality(personScholarityStringDTO.getNationality());
        person.setNaturalness(personScholarityStringDTO.getNaturalness());
        person.setBirthDate(personScholarityStringDTO.getBirthDate());
        person.setScholarity(scholarityTypeMapper.mapperScholarityType(personScholarityStringDTO.getScholarity()));
        person.setDependents(personScholarityStringDTO.getDependents());
        person.setFatherName(personScholarityStringDTO.getFatherName());
        person.setMotherName(personScholarityStringDTO.getMotherName());
        person.setUserData(personScholarityStringDTO.getUserData());
        person.setPersonPhoto(personScholarityStringDTO.getPersonPhoto());
        return person;
    }
}
