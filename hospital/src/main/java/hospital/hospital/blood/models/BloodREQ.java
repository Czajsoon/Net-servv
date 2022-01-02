package hospital.hospital.blood.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BloodREQ extends BloodBase {
    private Long user;
    private Date date;
}
