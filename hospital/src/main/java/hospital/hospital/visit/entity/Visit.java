package hospital.hospital.visit.entity;

import hospital.hospital.visitType.entity.VisitType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date data;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "visitType_id")
    private VisitType visitType;

}
