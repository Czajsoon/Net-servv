package hospital.hospital.blood.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BloodElement {
    private String name;
    private Float amount;
    private Boolean result;
    private String unit;
}
