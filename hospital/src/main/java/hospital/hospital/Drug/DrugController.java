package hospital.hospital.Drug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/drug")
public class DrugController {

    @Autowired
    DrugRepository drugRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Drug> drugGET(@PathVariable Long id){
        try { return ResponseEntity.ok().body(drugRepository.getById(id));}
        catch (NoSuchElementException ex){return ResponseEntity.ok().body(null);}
    }

    @GetMapping
    public ResponseEntity<List<Drug>> drugGET(){
        return ResponseEntity.ok().body(drugRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<String> drugPOST(@RequestBody DrugDTO dto){
        drugRepository.save(Drug.of(dto));
        return ResponseEntity.ok().body("Drug successfully uploaded!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Drug>> drugDELETE(@PathVariable Long id){
        drugRepository.deleteById(id);
        return ResponseEntity.ok().body(drugRepository.findAll());
    }
}
