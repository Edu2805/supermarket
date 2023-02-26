package br.com.amorim.supermarket.repository.department.generateinternalcoderepositorycustom;

import br.com.amorim.supermarket.model.department.Department;
import br.com.amorim.supermarket.model.department.QDepartment;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateInternalCodeDepartmentRepositoryCustomImpl implements
        GenerateInternalCodeDepartmentRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_CODE_ONE = 1;
    private static final int INCREASE_CODE_ONE = 1;

    @Override
    public BigInteger generateInternalCode(Department department) {
        QDepartment qDepartment = QDepartment.department;
        JPAQuery<Department> query = new JPAQuery<>(entityManager);
        BigInteger departmentDataQuery = query.select(qDepartment.code.max())
                .from(qDepartment).fetchOne();

        if (departmentDataQuery != null) {
            return departmentDataQuery.add(BigInteger.valueOf(INCREASE_CODE_ONE));
        }

        return BigInteger.valueOf(START_CODE_ONE);
    }
}
