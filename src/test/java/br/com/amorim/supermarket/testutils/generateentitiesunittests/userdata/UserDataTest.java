package br.com.amorim.supermarket.testutils.generateentitiesunittests.userdata;

import br.com.amorim.supermarket.common.enums.RoleType;
import br.com.amorim.supermarket.model.userdata.UserData;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class UserDataTest {

    public UserData generateUserData() {
        Random userName = new Random();
        var name = userName.nextInt(100000, 199999);
        UserData userData = new UserData();

        userData.setId(UUID.randomUUID());
        userData.setUserName(String.valueOf(name));
        userData.setPassword("123456");
        userData.setRole(RoleType.ADMIN);
        userData.setIsEmployee(true);
        userData.setRegistrationDate(Timestamp.from(Instant.now()));
        return userData;
    }
}
