package hospital.hospital.doctor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.doctor.models.DoctorREQ;
import hospital.hospital.operation.entity.Operation;
import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.refferalAbsention.entity.RefferalAbsention;
import hospital.hospital.specialisation.entity.Specialisation;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.visit.entity.Visit;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Table(name = "DOCTOR")
@Entity
@Data
public class Doctor {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Integer room;

    @ManyToMany
    @JoinTable(name = "DOC_SPEC",
    joinColumns = @JoinColumn(name = "doc_id"),
    inverseJoinColumns = @JoinColumn(name = "spec_id"))
    private List<Specialisation> specialisation;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Recipe> recipes;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<RefferalAbsention> refferalAbsentions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "DOC_OPER",
    joinColumns = @JoinColumn(name = "doc_id"),
    inverseJoinColumns = @JoinColumn(name = "operation_id"))
    private Set<Operation> operations;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Stay> stays;

    public static Doctor of(DoctorREQ doctorREQ){
        Doctor doctor = new Doctor();
        doctor.setName(doctorREQ.getName());
        doctor.setSurname(doctorREQ.getSurname());
        doctor.setRoom(doctorREQ.getRoom());
        return doctor;
    }

    public static Doctor dto(Doctor doctor){
        if(doctor.getSpecialisation() != null)doctor.getSpecialisation().forEach(specialisation1 -> specialisation1.setDoctor(null));
        doctor.setVisits(null);
        doctor.setRecipes(null);
        doctor.setStays(null);
        return doctor;
    }


}
