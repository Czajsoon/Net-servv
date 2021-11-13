package hospital.hospital.user.models;

import hospital.hospital.role.models.RoleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserModel {
    private String username;
    private String name;
    private String surname;
    private Date bornDate;
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();
}
