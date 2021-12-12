package hospital.hospital.operation.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.operation.models.OperationREQ;
import hospital.hospital.operationRoom.entity.OperationRoom;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String description;

    @ManyToMany(mappedBy = "operations")
    private Set<Doctor> doctors;

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> nurses;

    @ManyToOne
    private OperationRoom operationRoom;

    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    public static Operation of(OperationREQ req,
                               OperationRoom operationRoom,
                               User user,
                               Set<User> nurses,
                               Set<Doctor> docotrs){
        Operation operation = new Operation();
        operation.setDescription(req.getDescription());
        operation.setDate(req.getDate());
        operation.setOperationRoom(operationRoom);
        operation.setUser(user);
        operation.setNurses(nurses);
        operation.setDoctors(docotrs);
        return operation;
    }

    public static Operation dto(Operation operation){
        operation.getDoctors().forEach(doctor -> doctor.getSpecialisation().forEach(specialisation -> specialisation.setDoctor(null)));
        if(operation.getStay() != null){
            operation.getStay().setOperations(null);
            operation.getStay().setDoctor(null);
            operation.getStay().setUser(null);
        }
        return operation;
    }
}
