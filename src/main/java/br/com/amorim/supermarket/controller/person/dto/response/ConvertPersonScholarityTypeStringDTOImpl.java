package br.com.amorim.supermarket.controller.person.dto.response;

import br.com.amorim.supermarket.controller.userdata.dto.responseconfigurationdto.ConverterUserDataResponseMapper;
import br.com.amorim.supermarket.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.amorim.supermarket.configuration.internacionalizationmessages.ResourcesBundleMessages.getString;

@AllArgsConstructor

@Component
public class ConvertPersonScholarityTypeStringDTOImpl implements ConvertPersonScholarityTypeStringDTO {

    private ConverterUserDataResponseMapper converterUserDataResponseMapper;

    @Override
    public PersonScholarityTypeStringDTO mapper(Person person) {
        return mapperPerson(person);
    }

    private PersonScholarityTypeStringDTO mapperPerson(Person person) {
        var personScholarityTypeStringDTO = new PersonScholarityTypeStringDTO();
        personScholarityTypeStringDTO.setId(person.getId());
        personScholarityTypeStringDTO.setFirstName(person.getFirstName());
        personScholarityTypeStringDTO.setMiddleName(person.getMiddleName());
        personScholarityTypeStringDTO.setLastName(person.getLastName());
        personScholarityTypeStringDTO.setCpf(person.getCpf());
        personScholarityTypeStringDTO.setRg(person.getRg());
        personScholarityTypeStringDTO.setBirthDate(person.getBirthDate());
        personScholarityTypeStringDTO.setScholarity(getString(person.getScholarity().name()));
        personScholarityTypeStringDTO.setEmail(person.getEmail());
        personScholarityTypeStringDTO.setDependents(person.getDependents());
        personScholarityTypeStringDTO.setFatherName(person.getFatherName());
        personScholarityTypeStringDTO.setMotherName(person.getMotherName());
        personScholarityTypeStringDTO.setNaturalness(person.getNaturalness());
        personScholarityTypeStringDTO.setNationality(person.getNationality());
        var userDataMapper = converterUserDataResponseMapper.getUserDataMapper(person.getUserData());
        personScholarityTypeStringDTO.setUserData(userDataMapper);
        personScholarityTypeStringDTO.setPersonPhoto(person.getPersonPhoto());
        return personScholarityTypeStringDTO;
    }
}
