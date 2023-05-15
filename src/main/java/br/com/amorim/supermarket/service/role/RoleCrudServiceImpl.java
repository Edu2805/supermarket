package br.com.amorim.supermarket.service.role;

import br.com.amorim.supermarket.model.role.Role;
import br.com.amorim.supermarket.repository.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor

@Service
public class RoleCrudServiceImpl implements RoleCrudService {

    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
