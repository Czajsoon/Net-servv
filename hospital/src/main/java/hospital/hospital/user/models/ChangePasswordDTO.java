package hospital.hospital.user.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ChangePasswordDTO {
    Long id;
    String oldPassword;
    String newPassword;



}
