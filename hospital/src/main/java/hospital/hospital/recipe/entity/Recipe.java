package hospital.hospital.recipe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.RealisedRecipeDrugs.entity.RealisedDrug;
import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.drug.entity.Drug;
import hospital.hospital.recipe.models.RecipeREQ;
import hospital.hospital.user.entity.User;
import hospital.hospital.visit.entity.Visit;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "RECIPE")
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String Description;

    @Column
    private Boolean realised;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToMany
    @JoinTable(name = "Recipe_Drugs",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "drug_id"))
    private Set<Drug> drugs;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe")
    private List<RealisedDrug> realisedDrugs;

    public static Recipe of(RecipeREQ req, User user, Doctor doctor, Set<Drug> drugs,Visit visit){
        Recipe recipe = new Recipe();
        recipe.setDate(req.getDate());
        recipe.setDescription(req.getDescription());
        recipe.setUser(user);
        recipe.setDoctor(doctor);
        recipe.setDrugs(drugs);
        recipe.setVisit(visit);
        recipe.setRealised(req.getRealised());
        return recipe;
    }

    public static Recipe dto(Recipe recipe){
        recipe.getDoctor().setRecipes(null);
        recipe.getDoctor().setVisits(null);
        recipe.getVisit().setVisitType(null);
        recipe.getVisit().setRecipes(null);
        recipe.getVisit().setDoctor(null);
        recipe.getVisit().setUser(null);
        recipe.getVisit().setRefferalAbsentions(null);
        recipe.getUser().setPassword(null);
        recipe.getUser().setRefferalAbsentions(null);
        recipe.getDoctor().setRefferalAbsentions(null);
        recipe.getDoctor().getSpecialisation().forEach(specialisation -> specialisation.setDoctor(null));
        return recipe;
    }
}
