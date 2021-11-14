package hospital.hospital.jwt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequest
{
    private String userName;
    private String password;
}
