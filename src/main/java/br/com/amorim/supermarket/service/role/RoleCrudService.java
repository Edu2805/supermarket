package br.com.amorim.supermarket.service.role;

import br.com.amorim.supermarket.model.role.Role;

import java.util.List;

public interface RoleCrudService {

    List<Role> findAllRoles();
}
