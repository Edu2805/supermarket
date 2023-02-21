package br.com.amorim.supermarket.controller.jobposition.dto;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class ConvertJobPositionMapperImpl implements ConvertJobPositionMapper {

    private ModelMapper modelMapper;
    @Override
    public JobPosition createOrUpdateJobPositionMapper(JobPositionDTO jobPositionDTO) {
        return modelMapper.map(jobPositionDTO, JobPosition.class);
    }
}
