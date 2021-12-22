package hospital.hospital.recipe.services;

import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.recipe.repository.RecipeRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class NFZService {
    @Autowired
    private RecipeRepository recipeRepository;


    public Recipe findRecipe(Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElse(null);
    }

    public Boolean checkRecipeDate(Date recipeDate) {//sprawdzenie ważności daty recepty
        DateTime dt = new DateTime(recipeDate);
        DateTime now = new DateTime(new Date());
        DateTime validationDays = dt.plusDays(30);
        if(now.isBefore(validationDays.toInstant()) || now.isEqual(validationDays.toInstant())) return true;
        else return false;
    }
}
