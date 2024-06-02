package br.com.amorim.supermarket.repository.userdata.userdatarepositorycustom;

import br.com.amorim.supermarket.model.userdata.QUserData;
import br.com.amorim.supermarket.model.userdata.UserData;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class UserDataRepositoryCustomImpl implements UserDataRepositoryCustom {

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

    @Override
    public Page<UserData> findByIdAndIsApproved(int page, int size) {
        QUserData qUserData = QUserData.userData;
        JPAQuery<UserData> query = new JPAQuery<>(entityManager);
        QueryResults<UserData> results = query.select(qUserData)
                .from(qUserData)
                .where(qUserData.isApproved.eq(false))
                .offset(page)
                .limit(size)
                .fetchResults();
        return new PageImpl<>(results.getResults(), PageRequest.of(page, size), results.getTotal());
    }
}
