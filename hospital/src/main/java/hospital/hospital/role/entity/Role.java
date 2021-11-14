package hospital.hospital.role.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hospital.hospital.user.entity.User;
import hospital.hospital.role.models.RoleDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private String name;

    @JsonManagedReference//helps avoid circular dependency in bidirectional mapping
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();


    public static Role of(RoleDTO dto){
        Role privilege = new Role();
        privilege.setName(dto.getName());
        return privilege;
    }
}
