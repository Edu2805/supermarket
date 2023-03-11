package br.com.amorim.supermarket.repository.goodsreceipt.generatecontrolnumberrepositorycustom;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.model.goodsreceipt.QGoodsReceipt;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@AllArgsConstructor

@Repository
public class GenerateControlNumberGoodsReceiptRepositoryCustomImpl implements GenerateControlNumberGoodsReceiptRepositoryCustom {

    private EntityManager entityManager;
    private static final int START_REGISTER_NUMBER_ONE = 1;
    private static final int INCREASE_REGISTER_NUMBER_ONE = 1;

    @Override
    public BigInteger generateRegisterNumber(GoodsReceipt goodsReceipt) {
        QGoodsReceipt qGoodsReceipt = QGoodsReceipt.goodsReceipt;
        JPAQuery<GoodsReceipt> query = new JPAQuery<>(entityManager);
        BigInteger goodsReceiptDataQuery = query.select(qGoodsReceipt.controlNumber.max())
                .from(qGoodsReceipt).fetchOne();

        if (goodsReceiptDataQuery != null) {
            return goodsReceiptDataQuery.add(BigInteger.valueOf(INCREASE_REGISTER_NUMBER_ONE));
        }

        return BigInteger.valueOf(START_REGISTER_NUMBER_ONE);
    }
}
