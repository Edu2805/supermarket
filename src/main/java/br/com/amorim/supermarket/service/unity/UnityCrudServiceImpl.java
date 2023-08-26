package br.com.amorim.supermarket.service.unity;

import br.com.amorim.supermarket.model.unity.Unity;
import br.com.amorim.supermarket.repository.unity.UnityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class UnityCrudServiceImpl implements UnityCrudService {

    private UnityRepository unityRepository;

    @Override
    public List<Unity> findAllUnities() {
        return unityRepository.findAll();
    }
}
