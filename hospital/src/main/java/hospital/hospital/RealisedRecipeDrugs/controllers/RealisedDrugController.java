package hospital.hospital.RealisedRecipeDrugs.controllers;

import hospital.hospital.RealisedRecipeDrugs.entity.RealisedDrug;
import hospital.hospital.RealisedRecipeDrugs.models.RealisedDrugREQ;
import hospital.hospital.RealisedRecipeDrugs.models.RealisedRecipeDTO;
import hospital.hospital.RealisedRecipeDrugs.repository.RealiseDrugRepository;
import hospital.hospital.RealisedRecipeDrugs.services.RealiseRecipeService;
import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequestMapping("/api/realiseRecipe")
public class RealisedDrugController {
    @Autowired
    private RealiseDrugRepository realiseDrugRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RealiseRecipeService realiseRecipeService;

    @PostMapping
    public ResponseEntity<RealisedDrug> addRealisedDrug(@RequestBody RealisedDrugREQ req){
        Optional<Recipe> recipe = recipeRepository.findById(req.getRecipe());
        if(recipe.isPresent()){
            RealisedDrug drug = realiseDrugRepository.save(RealisedDrug.of(req, recipe.get()));
            return ResponseEntity.ok(RealisedDrug.dto(drug));
        }
        else return ResponseEntity.ok(null);//TODO
    }

    @PutMapping("/{id}/{identifier}")
    public ResponseEntity<?> realiseRecipe(@PathVariable Long id,@PathVariable Long identifier){
        RealisedRecipeDTO recipe = realiseRecipeService.realiseRecipe(id, identifier);
        if(recipe != null) return ResponseEntity.ok(recipe);
        else return ResponseEntity.ok("Error occurred!");
    }
}
