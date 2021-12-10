package hospital.hospital.refferalAbsention.entity;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.refferalAbsention.models.RefferalAbsentionREQ;
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

    public static RefferalAbsention of(RefferalAbsentionREQ req,Doctor doctor,User user,Visit visit){
        RefferalAbsention refferalAbsention = new RefferalAbsention();
        refferalAbsention.setStartDate(req.getStartDate());
        refferalAbsention.setEndDate(req.getEndDate());
        refferalAbsention.setDescription(req.getDescription());
        refferalAbsention.setUser(user);
        refferalAbsention.setDoctor(doctor);
        refferalAbsention.setVisit(visit);
        return refferalAbsention;
    }

    public static RefferalAbsention dto(RefferalAbsention ref){
        ref.getDoctor().setRecipes(null);
        ref.getDoctor().setVisits(null);
        ref.getDoctor().setSpecialisation(null);
        ref.getDoctor().setRefferalAbsentions(null);
        ref.getVisit().getVisitType().setVisits(null);
        ref.getVisit().setUser(null);
        ref.getVisit().setDoctor(null);
        ref.getVisit().setRecipes(null);
        ref.getVisit().setRefferalAbsentions(null);
        ref.getUser().setPassword(null);
        ref.getUser().setRefferalAbsentions(null);
        ref.getUser().setVisits(null);
        ref.getUser().setRecipes(null);
        return ref;
    }
}
