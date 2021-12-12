package hospital.hospital.stay.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.operation.entity.Operation;
import hospital.hospital.operation.repository.OperationRepository;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.stay.models.StayREQ;
import hospital.hospital.stay.repository.StayRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stay")
public class StayController {
    @Autowired
    private StayRepository stayRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OperationRepository operationRepository;

    @PostMapping
    public ResponseEntity<?> stay(@RequestBody StayREQ stayREQ){
        Optional<Doctor> doctor = doctorRepository.findById(stayREQ.getDoctor());
        Optional<User> user = userRepository.findById(stayREQ.getUser());
        if(doctor.isPresent() && user.isPresent()) {
            Stay save = stayRepository.save(Stay.of(stayREQ, doctor.get(), user.get()));
            return ResponseEntity.ok(Stay.dto(save));
        }
        else return ResponseEntity.ok("error");
    }

    @GetMapping
    public ResponseEntity<Set<Stay>> stay(){
        return ResponseEntity.ok(stayRepository.findAll().stream().map(Stay::dto).collect(Collectors.toSet()));
    }
}
