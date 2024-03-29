package br.com.amorim.supermarket.repository.userdata;

import br.com.amorim.supermarket.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {

    Optional<UserData> findByUserName(String userName);
    List<UserData> findByIsEmployee(boolean isEmployee);
}
