package br.com.amorim.supermarket.service.scholarity;

import br.com.amorim.supermarket.model.scholarity.Scholarity;
import br.com.amorim.supermarket.repository.scholarity.ScholarityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class ScholarityCrudServiceImpl implements ScholarityCrudService {

    private ScholarityRepository scholarityRepository;

    @Override
    public List<Scholarity> findAllEducations() {
        return scholarityRepository.findAll();
    }
}
