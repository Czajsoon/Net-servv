package hospital.hospital.stay.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.operation.entity.Operation;
import hospital.hospital.stay.models.StayREQ;
import hospital.hospital.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "HOSPITALISATION")
@Data
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "stay")
    private Set<Operation> operations;

    public static Stay of(StayREQ req,Doctor doctor,User user){
        Stay stay = new Stay();
        stay.setStartDate(req.getStartDate());
        stay.setEndDate(req.getEndDate());
        stay.setDescription(req.getDescription());
        stay.setDoctor(doctor);
        stay.setUser(user);
        return stay;
    }

    public static Stay dto(Stay stay){
        if(stay.getOperations() != null)stay.getOperations().forEach(operation -> {
            operation.setOperationRoom(null);
            operation.setDoctors(null);
            operation.setUser(null);
            operation.setNurses(null);
        });
        stay.getDoctor().setVisits(null);
        stay.getDoctor().setRecipes(null);
        stay.getDoctor().getSpecialisation().forEach(specialisation -> specialisation.setDoctor(null));
        stay.getDoctor().setRefferalAbsentions(null);
        stay.getDoctor().setOperations(null);
        stay.getUser().setRole(null);
        stay.getUser().setBlood(null);
        stay.getUser().setStays(null);
        stay.getUser().setRefferalAbsentions(null);
        stay.getUser().setRecipes(null);
        stay.getUser().setVisits(null);
        stay.getUser().setInOperations(null);
        stay.getUser().setResults(null);
        stay.getUser().setTakeOperations(null);
        return stay;
    }
}
