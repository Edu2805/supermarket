package br.com.amorim.supermarket.service.getsalenumber;

import br.com.amorim.supermarket.repository.goodsissue.generatesalenumber.GenerateSaleNumberRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@AllArgsConstructor

@Service
public class GetSaleNumberImpl implements GetSaleNumber{

    private GenerateSaleNumberRepositoryCustom generateSaleNumberRepositoryCustom;

    @Override
    public BigInteger generateSaleNumber() {
        return generateSaleNumberRepositoryCustom.generateSaleNumber();
    }
}
