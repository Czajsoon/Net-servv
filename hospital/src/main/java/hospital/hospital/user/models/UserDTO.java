package hospital.hospital.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import hospital.hospital.jwt.model.AuthorityModel;
import hospital.hospital.role.models.RoleDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements UserDetails {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private Date bornDate;
    private String password;
    private Set<RoleDTO> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<AuthorityModel> authorityModels = new HashSet<>();
        roles.forEach(role ->{
            authorityModels.add(AuthorityModel.builder().authority(role.getName()).build());
        });
        return authorityModels;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
