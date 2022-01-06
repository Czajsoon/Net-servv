package hospital.hospital.RealisedRecipeDrugs.services;

import hospital.hospital.RealisedRecipeDrugs.entity.RealisedDrug;
import hospital.hospital.RealisedRecipeDrugs.models.RealisedRecipeDTO;
import hospital.hospital.RealisedRecipeDrugs.repository.RealiseDrugRepository;
import hospital.hospital.drug.entity.Drug;
import hospital.hospital.drug.repositories.DrugRepository;
import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.recipe.repository.RecipeRepository;
import hospital.hospital.recipe.services.NFZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class RealiseRecipeService {
    @Autowired
    private NFZService nfzService;
    @Autowired
    private RealiseDrugRepository realiseDrugRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    //TODO dodac sprawdzenie czy nie minął czas lekarstwa na recepcie
    public RealisedRecipeDTO realiseRecipe(Long id, Long identifier){
        Recipe recipe = nfzService.findRecipe(id);
        if (recipe != null){
            RealisedRecipeDTO realisedRecipeDTO = new RealisedRecipeDTO();
            if(recipe.getUser().getIdentification().equals(identifier)){
                if(nfzService.checkRecipeDate(recipe.getDate())){
                    recipe.getRealisedDrugs().forEach(realisedDrug -> {
                        Optional<Drug> drug = drugRepository.findById(realisedDrug.getDrugId());
                        if(drug.get().getAmount_in_warehouse()>0 && !realisedDrug.getRealised()){
                            drug.get().setAmount_in_warehouse(drug.get().getAmount_in_warehouse()-1);
                            if(realisedDrug.getDrugId().equals(drug.get().getId())){
                                realisedDrug.setRealised(true);
                                realisedRecipeDTO.getRealisedDrugs().add(Drug.dto(drug.get()));
                                realiseDrugRepository.save(realisedDrug);
                            }
                        }
                        else if(realisedDrug.getRealised()){
                            if(realisedDrug.getDrugId().equals(drug.get().getId())){
                                realisedRecipeDTO.getRealisedDrugs().add(Drug.dto(drug.get()));
                            }
                        }
                        else{
                            if(realisedDrug.getDrugId().equals(drug.get().getId())){
                                realisedRecipeDTO.getNoRealisedDrugs().add(Drug.dto(drug.get()));
                            }
                        }
                    });
                    if(recipe.getDrugs().size() == realisedRecipeDTO.getRealisedDrugs().size()){
                        realisedRecipeDTO.setAllRealised(true);
                        recipe.setRealised(true);
                        recipeRepository.save(recipe);
                    }
                    return realisedRecipeDTO;
                }
                else return null;
            }
            else return null;
        }
        else return null;
    }
}
