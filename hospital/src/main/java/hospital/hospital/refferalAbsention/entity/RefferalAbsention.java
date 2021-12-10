package hospital.hospital.refferalAbsention.entity;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.user.entity.User;
import hospital.hospital.visit.entity.Visit;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class RefferalAbsention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
}
