package br.com.amorim.supermarket.service.jobposition.filljobpositionname.fillname;

import br.com.amorim.supermarket.model.jobposition.JobPosition;
import br.com.amorim.supermarket.repository.salary.SalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@Component
public class FillPositionNameBySalaryImpl implements FillPositionNameBySalary {

    private SalaryRepository salaryRepository;
    @Override
    public void fillPositionName(JobPosition jobPosition) {
        var findSalary = salaryRepository.findById(jobPosition.getSalary().getId());
        findSalary.ifPresent(salary -> jobPosition.setName(salary.getPosition()));
    }
}
