package hospital.hospital.visit.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import hospital.hospital.visit.entity.Visit;
import hospital.hospital.visit.models.VisitREQ;
import hospital.hospital.visit.repository.VisitRepository;
import hospital.hospital.visitType.entity.VisitType;
import hospital.hospital.visitType.repository.VisitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private VisitTypeRepository visitTypeRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> visits(@RequestBody VisitREQ visitREQ){
        Optional<VisitType> visitType = visitTypeRepository.findById(visitREQ.getVisitType());
        Optional<Doctor> doctor = doctorRepository.findById(visitREQ.getDoctor());
        Optional<User> user = userRepository.findById(visitREQ.getUser());
        if(visitType.isPresent() && doctor.isPresent() && user.isPresent()){
            Visit visit = visitRepository.save(Visit.of(visitREQ, visitType.get(), doctor.get(), user.get()));
            return ResponseEntity.ok(Visit.dto(visit));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> visits(){
        return ResponseEntity.ok(visitRepository.findAll().stream().map(Visit::dto).collect(Collectors.toSet()));
    }

}
