package br.com.amorim.supermarket.repository.goodsissue.generatesalenumber;

import br.com.amorim.supermarket.model.goodsissue.GoodsIssue;
import br.com.amorim.supermarket.model.goodsissue.QGoodsIssue;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateSaleNumberRepositoryCustomImpl implements GenerateSaleNumberRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_SALE_NUMBER_ONE = 1;
    private static final int INCREASE_SALE_NUMBER_ONE = 1;

    @Override
    public BigInteger generateSaleNumber(GoodsIssue goodsIssue) {
        QGoodsIssue qGoodsIssue = QGoodsIssue.goodsIssue;
        JPAQuery<GoodsIssue> query = new JPAQuery<>(entityManager);
        BigInteger goodsIssueDataQuery = query.select(qGoodsIssue.saleNumber.max())
                .from(qGoodsIssue).fetchOne();

        if (goodsIssueDataQuery != null) {
            return goodsIssueDataQuery.add(BigInteger.valueOf(INCREASE_SALE_NUMBER_ONE));
        }

        return BigInteger.valueOf(START_SALE_NUMBER_ONE);
    }
}
