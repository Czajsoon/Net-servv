package hospital.hospital.doctor.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.doctor.models.DoctorREQ;
import hospital.hospital.specialisation.entity.Specialisation;
import hospital.hospital.visit.entity.Visit;
import lombok.*;

import javax.persistence.*;
import javax.print.Doc;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class Doctor {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToMany
    @JoinTable(name = "DOC_SPEC",
    joinColumns = @JoinColumn(name = "doc_id"),
    inverseJoinColumns = @JoinColumn(name = "spec_id"))
    private List<Specialisation> specialisation;

    @OneToMany(mappedBy = "doctor")
    private Set<Visit> visits;

    public static Doctor of(DoctorREQ doctorREQ){
        Doctor doctor = new Doctor();
        doctor.setName(doctorREQ.getName());
        doctor.setSurname(doctorREQ.getSurname());
        return doctor;
    }

    public static Doctor response(Doctor doctor){
        if(doctor.getSpecialisation() != null)doctor.getSpecialisation().forEach(specialisation1 -> specialisation1.setDoctor(null));
        if(doctor.getVisits() != null) doctor.getVisits().forEach(visit -> visit.setDoctor(null));
        return doctor;
    }


}
