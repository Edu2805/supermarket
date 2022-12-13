package br.com.amorim.supermarket.model.subsection;

import br.com.amorim.supermarket.model.productdata.ProductData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "subsection")
public class SubSection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Nome não pode estar vazio")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Código não pode estar vazio")
    private BigInteger code;

    @OneToMany(mappedBy = "subSection", fetch = FetchType.LAZY)
    List<ProductData> productDataList;
}
