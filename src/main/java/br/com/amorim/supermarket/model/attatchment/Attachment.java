package br.com.amorim.supermarket.model.attatchment;

import br.com.amorim.supermarket.model.common.CommonIdEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "attachment")
public class Attachment extends CommonIdEntity{

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "type", length = 1000)
    private String type;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;
}
