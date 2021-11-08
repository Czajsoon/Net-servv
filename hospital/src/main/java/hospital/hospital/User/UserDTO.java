package hospital.hospital.User;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private Date bornDate;
    private String password;
    private Long privilegeId;
}
