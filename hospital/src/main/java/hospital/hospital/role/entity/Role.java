package hospital.hospital.role.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.user.entity.User;
import hospital.hospital.role.models.RoleDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Data
@Table(name = "ROLE")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private String name;

    @JsonIgnore//helps avoid circular dependency in bidirectional mapping
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public static Role of(RoleDTO dto){
        Role privilege = new Role();
        privilege.setName(dto.getName());
        return privilege;
    }
}
