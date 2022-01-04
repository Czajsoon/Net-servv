package hospital.hospital.specialisation.controllers;

import hospital.hospital.specialisation.entity.Specialisation;
import hospital.hospital.specialisation.models.SpecialisationREQ;
import hospital.hospital.specialisation.repository.SpecialisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/specialisation")
public class SpecialisationController {
    @Autowired
    private SpecialisationRepository specialisationRepository;

    @PostMapping
    public ResponseEntity<?> specialisation(@RequestBody SpecialisationREQ specialisationREQ){
        specialisationRepository.save(Specialisation.of(specialisationREQ));
        return ResponseEntity.ok("Specialisation created!");
    }

    @GetMapping
    public ResponseEntity<?> specialization(){
        return ResponseEntity.ok(specialisationRepository.findAll().stream().map(Specialisation::dto));
    }

    @PostConstruct
    public void init(){
        SpecialisationREQ spec1 = SpecialisationREQ.builder().name("Chirurg").build();
        SpecialisationREQ spec2 = SpecialisationREQ.builder().name("Kardiolog").build();
        SpecialisationREQ spec3 = SpecialisationREQ.builder().name("Onkolog").build();
        SpecialisationREQ spec4 = SpecialisationREQ.builder().name("Okulista").build();
        specialisation(spec1);
        specialisation(spec2);
        specialisation(spec3);
        specialisation(spec4);
    }
}
