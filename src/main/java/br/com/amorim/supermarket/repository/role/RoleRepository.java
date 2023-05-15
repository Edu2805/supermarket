package br.com.amorim.supermarket.repository.role;

import br.com.amorim.supermarket.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
