package hospital.hospital.drug.controllers;

import hospital.hospital.drug.entity.Drug;
import hospital.hospital.drug.models.DrugDTO;
import hospital.hospital.drug.repositories.DrugRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/drug")
@Slf4j
public class DrugController {

    @Autowired
    DrugRepository drugRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Drug> drugGET(@PathVariable Long id){
        try {
            log.info("GET: " + drugRepository.getById(id));
            return ResponseEntity.ok().body(drugRepository.getById(id));
        }
        catch (NoSuchElementException ex){return ResponseEntity.ok().body(null);}
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Drug>> drugGET(){
        log.info("GET: " + drugRepository.findAll());
        return ResponseEntity.ok().body(drugRepository.findAll());
    }

    @PostMapping
    public ResponseEntity drugPOST(@RequestBody DrugDTO dto){
        drugRepository.save(Drug.of(dto));
        log.info("POST: " + dto);
        return ResponseEntity.ok().body("Drug successfully uploaded!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Drug>> drugDELETE(@PathVariable Long id){
        log.info("DELETE: " + drugRepository.getById(id));
        drugRepository.deleteById(id);
        return ResponseEntity.ok().body(drugRepository.findAll());
    }
}
