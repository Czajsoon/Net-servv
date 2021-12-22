package hospital.hospital.doctor.models;

import lombok.Data;

import java.util.Set;

@Data
public class DoctorsREQ {
    private Set<DoctorREQ> doctors;
}
