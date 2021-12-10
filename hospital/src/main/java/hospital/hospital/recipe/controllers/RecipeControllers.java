package hospital.hospital.recipe.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.drug.entity.Drug;
import hospital.hospital.drug.repositories.DrugRepository;
import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.recipe.models.RecipeREQ;
import hospital.hospital.recipe.repository.RecipeRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import hospital.hospital.visit.entity.Visit;
import hospital.hospital.visit.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeControllers {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private VisitRepository visitRepository;

    @PostMapping
    public ResponseEntity<?> recipe(@RequestBody RecipeREQ req){
        Set<Drug> drugsList = new HashSet<>();
        Optional<User> user = userRepository.findById(req.getUser());
        Optional<Doctor> doctor = doctorRepository.findById(req.getDoctor());
        Optional<Visit> visit = visitRepository.findById(req.getVisit());
        req.getDrugs().forEach(drug ->{
            Optional<Drug> optionalDrug = drugRepository.findById(drug);
            optionalDrug.ifPresent(drugsList::add);
        });
        if(user.isPresent() && doctor.isPresent() && visit.isPresent()){
            Recipe save = recipeRepository.save(Recipe.of(req, user.get(),doctor.get(),drugsList,visit.get()));
            return ResponseEntity.ok(Recipe.dto(save));
        }
        else return ResponseEntity.ok("error");
    }
}
