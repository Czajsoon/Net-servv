package hospital.hospital.User;

import hospital.hospital.Privilege.Privilege;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private Date bornDate;
    @Column
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    private Privilege privilege;

    public static User of(UserDTO dto,Privilege privilege) {
        User user = new User();
        //user.setId(dto.getId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setBornDate(dto.getBornDate());
        user.setPassword(dto.getPassword());
        user.setPrivilege(privilege);
        return user;
    }
}
