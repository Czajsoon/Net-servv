package hospital.hospital.blood.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BloodREQ extends BloodBase {
    private Long user;
}
