package hospital.hospital.doctor.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.doctor.models.DoctorREQ;
import hospital.hospital.specialisation.entity.Specialisation;
import lombok.*;

import javax.persistence.*;
import javax.print.Doc;
import java.util.List;


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

    public static Doctor of(DoctorREQ doctorREQ){
        Doctor doctor = new Doctor();
        doctor.setName(doctorREQ.getName());
        doctor.setSurname(doctorREQ.getSurname());
        return doctor;
    }

    public static Doctor response(Doctor doctor){
        doctor.getSpecialisation().forEach(specialisation1 -> specialisation1.setDoctor(null));
        return doctor;
    }


}
