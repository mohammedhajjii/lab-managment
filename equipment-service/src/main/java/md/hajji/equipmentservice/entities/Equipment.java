package md.hajji.equipmentservice.entities;


import jakarta.persistence.*;
import lombok.*;
import md.hajji.equipmentservice.enums.EquipmentStatus;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Equipment {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;
    private String name;
    @Lob
    @Column(length = 16777215)
    private byte[] image;
    @Lob
    @Column(length = 16777215)
    private byte[] qrcode;
    private boolean restricted;

    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @ManyToOne
    private Category category;
}
