package br.com.amorim.supermarket.service.salary.calculatetax.calculateinss.progressiverate;

import br.com.amorim.supermarket.model.salary.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static br.com.amorim.supermarket.service.salary.calculatetax.utils.TaxRangeConstants.*;

@AllArgsConstructor

@Component
public class CalculateProgressiveRateImpl implements CalculateProgressiveRate {

    /**
     * Na nova tabela de cálculo da contribuição, deve-se multiplicar pela alíquota de cada faixa apenas a parcela
     * do salário que nela se encaixar.
     * <p>
     * Por exemplo, veja como fica o cálculo para um salário de R$ 3.000,00 no ano de 2023:
     * <p>
     * 1ª faixa salarial: 1.302,00 x 0,075 = 97,65
     * 2ª faixa salarial: [2.571,29 – 1.302,00] x 0,09 = 1.269,29 x 0,09 = 114,23
     * Faixa que atinge o salário: [3.000,00 – 2,571,29] x 0,12 = 428,71 x 0,12 = 51,45
     * Total a recolher: 97,65 + 114,23 + 51,45 = 263,33
     * Exemplificando:
     * <p>
     * 7,5% de R$ 1.302,00  (por seu salário ter ultrapassado a primeira faixa), que corresponde a uma contribuição de R$ 97,65; mais
     * <p>
     * 9% sobre R$ 1.269,29 (esse valor refere-se a diferença de valores da segunda faixa: [2.571,29 – 1.302,00] , uma vez que
     * o salário da segurada ultrapassou esta faixa também), que corresponde a uma contribuição de R$ 114,23 mais
     * <p>
     * 12% sobre R$ 428,71 (valor residual do salário do segurado após passar pelas duas faixas:
     * R$ 3.000,00 – R$ 1.302,00 – R$ 1.269,294), que corresponde a uma contribuição de R$ 51,45.
     *
     * @param salary entidade salário que irá prover o salário bruto para o cálculo
     * @param taxRange faixa da aliquota que o salário bruto corresponde
     * @return valor calculado do INSS progressivo
     */
    @Override
    public BigDecimal progressiveCalculation(Salary salary, BigDecimal taxRange) {
        BigDecimal rangeOne;
        BigDecimal rangeTwo;
        BigDecimal rangeThree;
        BigDecimal rangeFour;
        if (taxRange.equals(INSS_TAX_RANGE_1)) {
            return salary.getGrossSalary().multiply(INSS_TAX_RANGE_1);
        } else if (taxRange.equals(INSS_TAX_RANGE_2)) {
            rangeOne = INSS_RANGE_1.multiply(INSS_TAX_RANGE_1);
            rangeTwo = (salary.getGrossSalary().subtract(INSS_RANGE_1)).multiply(INSS_TAX_RANGE_2);
            return rangeOne.add(rangeTwo);
        } else if (taxRange.equals(INSS_TAX_RANGE_3)) {
            rangeOne = INSS_RANGE_1.multiply(INSS_TAX_RANGE_1);
            rangeTwo = (INSS_RANGE_2.subtract(INSS_RANGE_1)).multiply(INSS_TAX_RANGE_2);
            rangeThree = (salary.getGrossSalary().subtract(INSS_RANGE_2)).multiply(INSS_TAX_RANGE_3);
            return rangeOne.add(rangeTwo).add(rangeThree);
        } else if ((salary.getGrossSalary().compareTo(INSS_RANGE_4) < ZERO
                || salary.getGrossSalary().compareTo(INSS_RANGE_4) == ZERO)
                && taxRange.equals(INSS_TAX_RANGE_4)) {
            rangeOne = INSS_RANGE_1.multiply(INSS_TAX_RANGE_1);
            rangeTwo = (INSS_RANGE_2.subtract(INSS_RANGE_1)).multiply(INSS_TAX_RANGE_2);
            rangeThree = (INSS_RANGE_3.subtract(INSS_RANGE_2)).multiply(INSS_TAX_RANGE_3);
            rangeFour = (salary.getGrossSalary().subtract(INSS_RANGE_3)).multiply(INSS_TAX_RANGE_4);
            return rangeOne.add(rangeTwo).add(rangeThree).add(rangeFour);
        } else if ((salary.getGrossSalary().compareTo(INSS_RANGE_5) > ZERO
                || salary.getGrossSalary().compareTo(INSS_RANGE_5) == ZERO)
                && taxRange.equals(INSS_TAX_RANGE_4)) {
            rangeOne = INSS_RANGE_1.multiply(INSS_TAX_RANGE_1);
            rangeTwo = (INSS_RANGE_2.subtract(INSS_RANGE_1)).multiply(INSS_TAX_RANGE_2);
            rangeThree = (INSS_RANGE_3.subtract(INSS_RANGE_2)).multiply(INSS_TAX_RANGE_3);
            rangeFour = (INSS_RANGE_4.subtract(INSS_RANGE_3)).multiply(INSS_TAX_RANGE_4);
            return rangeOne.add(rangeTwo).add(rangeThree).add(rangeFour);
        } else {
            return INSS_NO_TAX_RANGE;
        }
    }
}
