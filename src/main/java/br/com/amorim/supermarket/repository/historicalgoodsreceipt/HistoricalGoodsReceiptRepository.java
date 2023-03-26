package br.com.amorim.supermarket.repository.historicalgoodsreceipt;

import br.com.amorim.supermarket.model.historicalgoodsreceipt.HistoricalGoodsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface HistoricalGoodsReceiptRepository extends JpaRepository<HistoricalGoodsReceipt, UUID> {

//    @Query(
//            "SELECT SUM(hgr.purchasePrice * hgr.inventory) " +
//                    "FROM HistoricalGoodsReceipt AS hgr " +
//                    "WHERE hgr.providerProductName = ?1 " +
//                    "AND hgr.departmentName = ?2 " +
//                    "AND hgr.mainsectionName = ?3 " +
//                    "AND hgr.subsectionName = ?4 " +
//                    "AND hgr.productCode = ?5 " +
//                    "AND hgr.registrationDate BETWEEN ?6 AND ?7 " +
//                    "AND hgr.isReceived = ?8 "
//    )
//    BigDecimal expensiesReportQuery1(String providerProductName, String departmentName, String mainsectionName,
//                                     String subsectionName, BigInteger productCode, Timestamp from, Timestamp to,
//                                     boolean received);
}
