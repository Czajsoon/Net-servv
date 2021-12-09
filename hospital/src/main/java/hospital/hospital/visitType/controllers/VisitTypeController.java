package hospital.hospital.visitType.controllers;

import hospital.hospital.visitType.entity.VisitType;
import hospital.hospital.visitType.models.VisitTypeREQ;
import hospital.hospital.visitType.repository.VisitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/visitType")
public class VisitTypeController {
    @Autowired
    private VisitTypeRepository visitTypeRepository;

    @PostMapping
    public ResponseEntity<?> visitType(@RequestBody VisitTypeREQ visitTypeREQ){
        return ResponseEntity.ok(visitTypeRepository.save(VisitType.of(visitTypeREQ)));
    }

    @GetMapping
    public ResponseEntity<?> visitType(){
        return ResponseEntity.ok(visitTypeRepository.findAll().stream().map(VisitType::response));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> visitType(@PathVariable Long id){
        Optional<VisitType> visitType = visitTypeRepository.findById(id);
        if(visitType.isPresent()){
            return ResponseEntity.ok(visitType.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostConstruct
    public void init(){
        VisitTypeREQ visitType = new VisitTypeREQ();
        VisitTypeREQ visitType1 = new VisitTypeREQ();
        visitType.setName("Teleporada");
        visitType1.setName("Tradycyjna");
        visitType(visitType);
        visitType(visitType1);
    }
}
