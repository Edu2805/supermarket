package br.com.amorim.supermarket.repository.goodsreceipt.verifyinvoicerepositorycustom;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import br.com.amorim.supermarket.model.goodsreceipt.QGoodsReceipt;
import br.com.amorim.supermarket.model.userdata.UserData;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@AllArgsConstructor

@Repository
public class VerifyInvoiceRepositoryCustomImpl implements VerifyInvoiceRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public boolean existsByInvoice(GoodsReceipt goodsReceipt) {
        QGoodsReceipt qGoodsReceipt = QGoodsReceipt.goodsReceipt;
        JPAQuery<UserData> query = new JPAQuery<>(entityManager);
        return !query.select(qGoodsReceipt)
                .from(qGoodsReceipt)
                .where(qGoodsReceipt.invoice.eq(goodsReceipt.getInvoice()))
                .fetch().isEmpty();
    }
}
