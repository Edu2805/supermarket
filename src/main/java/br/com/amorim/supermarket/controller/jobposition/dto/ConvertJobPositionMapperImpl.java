package br.com.amorim.supermarket.controller.jobposition.dto;

import br.com.amorim.supermarket.controller.jobposition.dto.request.JobPositionDTO;
import br.com.amorim.supermarket.controller.jobposition.dto.response.JobPositionListOutputDTO;
import br.com.amorim.supermarket.model.jobposition.JobPosition;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Component
public class ConvertJobPositionMapperImpl implements ConvertJobPositionMapper {

    private ModelMapper modelMapper;
    @Override
    public JobPosition createOrUpdateJobPositionMapper(JobPositionDTO jobPositionDTO) {
        return modelMapper.map(jobPositionDTO, JobPosition.class);
    }

    @Override
    public List<JobPositionListOutputDTO> jobPositionsAvailable(List<JobPosition> jobPositions) {
        List<JobPositionListOutputDTO> jobPositionListOutputDTOList = new ArrayList<>();
        if (!jobPositions.isEmpty()) {
            jobPositions.forEach(jobPosition -> jobPositionListOutputDTOList.add(modelMapper.map(jobPosition, JobPositionListOutputDTO.class)));
            return jobPositionListOutputDTOList;
        }
        return List.of();
    }
}
