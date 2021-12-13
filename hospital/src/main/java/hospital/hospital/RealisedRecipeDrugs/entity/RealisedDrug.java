package hospital.hospital.RealisedRecipeDrugs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.RealisedRecipeDrugs.models.RealisedDrugREQ;
import hospital.hospital.recipe.entity.Recipe;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Data
public class RealisedDrug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long drugId;

    @Column
    private Boolean realised;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;

    public static RealisedDrug of(RealisedDrugREQ req,Recipe recipe){
        RealisedDrug realisedDrug = new RealisedDrug();
        realisedDrug.setRealised(false);
        realisedDrug.setDrugId(req.getDrug());
        realisedDrug.setRecipe(recipe);
        return realisedDrug;
    }

    public static RealisedDrug dto(RealisedDrug realisedDrug){
        realisedDrug.setRecipe(null);
        return realisedDrug;
    }
}
