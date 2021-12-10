package hospital.hospital.blood.entity;

import hospital.hospital.blood.models.BloodREQ;
import hospital.hospital.user.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Blood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Blood of(BloodREQ req,User user){
        Blood blood = new Blood();
        blood.setUser(user);
        return blood;
    }


}
