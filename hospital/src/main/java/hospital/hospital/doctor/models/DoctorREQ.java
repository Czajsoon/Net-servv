package hospital.hospital.doctor.models;

import hospital.hospital.user.models.UserDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DoctorREQ extends UserDTO {
    private String name;
    private String surname;
    private Set<Long> specialisations;
}
