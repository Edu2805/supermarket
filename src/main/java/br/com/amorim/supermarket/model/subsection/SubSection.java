package br.com.amorim.supermarket.model.subsection;

import br.com.amorim.supermarket.model.mainsection.MainSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
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
    @NotEmpty(message = "{br.com.supermarket.SUB_SECTION_FIELD_NAME_IS_NOT_EMPTY}")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "{br.com.supermarket.SUB_SECTION_FIELD_INTERNAL_CODE_IS_NOT_EMPTY}")
    private BigInteger code;

    @ManyToOne
    @JoinColumn(name = "main_section")
    private MainSection mainSection;

}
