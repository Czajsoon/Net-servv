package hospital.hospital.RealisedRecipeDrugs.models;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RealisedDrugREQ {
    private Long drug;
    private Boolean realised;
    private Long Recipe;
}
