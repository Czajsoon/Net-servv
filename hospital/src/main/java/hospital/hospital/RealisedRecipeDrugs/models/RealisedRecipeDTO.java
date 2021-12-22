package hospital.hospital.RealisedRecipeDrugs.models;

import hospital.hospital.drug.entity.Drug;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RealisedRecipeDTO {
    List<Drug> realisedDrugs = new ArrayList<>();
    List<Drug> alreadyRealisedDrugs = new ArrayList<>();
    List<Drug> noRealisedDrugs = new ArrayList<>();
    Boolean allRealised = false;
}
