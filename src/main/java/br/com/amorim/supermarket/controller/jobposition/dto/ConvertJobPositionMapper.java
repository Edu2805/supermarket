package br.com.amorim.supermarket.controller.jobposition.dto;

import br.com.amorim.supermarket.model.jobposition.JobPosition;

public interface ConvertJobPositionMapper {

    JobPosition createOrUpdateJobPositionMapper(JobPositionDTO jobPositionDTO);
}
