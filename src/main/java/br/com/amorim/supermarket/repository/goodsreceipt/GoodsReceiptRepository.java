package br.com.amorim.supermarket.repository.goodsreceipt;

import br.com.amorim.supermarket.model.goodsreceipt.GoodsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GoodsReceiptRepository extends JpaRepository<GoodsReceipt, UUID> {
}
