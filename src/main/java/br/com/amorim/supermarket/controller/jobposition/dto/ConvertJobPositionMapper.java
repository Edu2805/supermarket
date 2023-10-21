package br.com.amorim.supermarket.controller.jobposition.dto;

import br.com.amorim.supermarket.controller.jobposition.dto.request.JobPositionDTO;
import br.com.amorim.supermarket.controller.jobposition.dto.response.JobPositionListOutputDTO;
import br.com.amorim.supermarket.model.jobposition.JobPosition;

import java.util.List;

public interface ConvertJobPositionMapper {

    JobPosition createOrUpdateJobPositionMapper(JobPositionDTO jobPositionDTO);

    List<JobPositionListOutputDTO> jobPositionsAvailable(List<JobPosition> jobPositions);
}
