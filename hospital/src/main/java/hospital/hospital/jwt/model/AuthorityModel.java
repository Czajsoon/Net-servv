package hospital.hospital.jwt.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
public class AuthorityModel implements GrantedAuthority {
    private String authority;
}
