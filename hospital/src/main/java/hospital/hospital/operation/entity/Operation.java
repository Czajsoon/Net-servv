package hospital.hospital.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.operation.models.OperationDTO;
import hospital.hospital.operation.models.OperationREQ;
import hospital.hospital.operationRoom.entity.OperationRoom;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.user.entity.User;
import lombok.Data;
import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "OPERATION")
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
    private List<Doctor> doctors;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> nurses;

    @JsonIgnore
    @ManyToOne
    private OperationRoom operationRoom;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stay_id")
    private Stay stay;

    public static Operation of(OperationDTO req){
        Operation operation = new Operation();
        operation.setDescription(req.getDescription());
        operation.setDate(req.getDate());
        operation.setOperationRoom(req.getOperationRoom());
        operation.setUser(req.getPatient());
        operation.setNurses(req.getNursesList());
        operation.setDoctors(req.getDoctorList());
        operation.setStay(req.getHospitalization());
        return operation;
    }

    public static Operation dto(Operation operation){
        operation.getDoctors().forEach(doctor -> {
            doctor.getSpecialisation().forEach(specialisation -> specialisation.setDoctor(null));
            doctor.setVisits(null);
            doctor.setRefferalAbsentions(null);
            doctor.setRecipes(null);
            doctor.setOperations(null);
            doctor.setStays(null);
        });
        if(operation.getStay() != null){
            operation.getStay().setOperations(null);
            operation.getStay().setDoctor(null);
            operation.getStay().setUser(null);
        }
        return operation;
    }
}
