package hospital.hospital.Privilege;

import hospital.hospital.User.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @OneToMany
    private List<User> users;


    public static Privilege of(PrivilegeDTO dto){
        Privilege privilege = new Privilege();
        privilege.setName(dto.getName());
        return privilege;
    }
}
