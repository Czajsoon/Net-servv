package hospital.hospital.refferalAbsention.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.refferalAbsention.entity.RefferalAbsention;
import hospital.hospital.refferalAbsention.models.RefferalAbsentionREQ;
import hospital.hospital.refferalAbsention.repository.RefferalAbsentionRepository;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/refferalAbsention")
public class RefferalAbsentionController {
    @Autowired
    private RefferalAbsentionRepository refferalAbsentionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VisitRepository visitRepository;

    @PostMapping
    public ResponseEntity<?> ra(@RequestBody RefferalAbsentionREQ req){
        Optional<User> user = userRepository.findById(req.getUser());
        Optional<Doctor> doctor = doctorRepository.findById(req.getDoctor());
        Optional<Visit> visit = visitRepository.findById(req.getVisit());
        if(user.isPresent() && doctor.isPresent() && visit.isPresent()){
            RefferalAbsention save = refferalAbsentionRepository.save(RefferalAbsention.of(req, doctor.get(), user.get(), visit.get()));
            return ResponseEntity.ok(RefferalAbsention.dto(save));
        }
        else return ResponseEntity.ok("error");//TODO
    }
}
