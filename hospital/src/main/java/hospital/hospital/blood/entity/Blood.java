package hospital.hospital.blood.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.blood.models.BloodBase;
import hospital.hospital.blood.models.BloodREQ;
import hospital.hospital.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "BLOOD_RESULTS")
@Data
public class Blood extends BloodBase {
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
