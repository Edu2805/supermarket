package br.com.amorim.supermarket.repository.userdata.verifyusernamerepositorycustom;

import br.com.amorim.supermarket.model.userdata.QUserData;
import br.com.amorim.supermarket.model.userdata.UserData;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class VerifyUserNameRepositoryCustomImpl implements VerifyUserNameRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean isUserNameAlreadyExistsInTheDatabase(UserData userData) {
        QUserData qUserData = QUserData.userData;
        JPAQuery<UserData> query = new JPAQuery<>(entityManager);
        return !query.select(qUserData)
                .from(qUserData)
                .where(qUserData.userName.eq(userData.getUserName()))
                .fetch().isEmpty();
    }
}
