package hospital.hospital.recipe.entity;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.drug.entity.Drug;
import hospital.hospital.recipe.models.RecipeREQ;
import hospital.hospital.user.entity.User;
import hospital.hospital.visit.entity.Visit;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String Description;

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

    public static Recipe of(RecipeREQ req, User user, Doctor doctor, Set<Drug> drugs,Visit visit){
        Recipe recipe = new Recipe();
        recipe.setDate(req.getDate());
        recipe.setDescription(req.getDescription());
        recipe.setUser(user);
        recipe.setDoctor(doctor);
        recipe.setDrugs(drugs);
        recipe.setVisit(visit);
        return recipe;
    }

    public static Recipe dto(Recipe recipe){
        recipe.getDoctor().setRecipes(null);
        recipe.getDoctor().setVisits(null);
        recipe.getVisit().setVisitType(null);
        recipe.getVisit().setRecipes(null);
        recipe.getVisit().setDoctor(null);
        recipe.getVisit().setUser(null);
        recipe.getDoctor().getSpecialisation().forEach(specialisation -> specialisation.setDoctor(null));
        return recipe;
    }
}
