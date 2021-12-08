package hospital.hospital.specialisation.entity;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.specialisation.models.SpecialisationREQ;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Specialisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "specialisation")
    private List<Doctor> doctor;

    public static Specialisation of(SpecialisationREQ specialisationREQ){
        Specialisation specialisation = new Specialisation();
        specialisation.setName(specialisationREQ.getName());
        return specialisation;
    }
}
