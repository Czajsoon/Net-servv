package hospital.hospital.operation.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.operation.entity.Operation;
import hospital.hospital.operation.models.OperationREQ;
import hospital.hospital.operation.repository.OperationRepository;
import hospital.hospital.operationRoom.entity.OperationRoom;
import hospital.hospital.operationRoom.repository.OperationRoomRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/operation")
public class OperationController {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private OperationRoomRepository operationRoomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<?> operation(@RequestBody OperationREQ req){
        Set<User> nurses = new HashSet<>();
        Set<Doctor> doctors = new HashSet<>();
        Optional<User> user = userRepository.findById(req.getUser());
        Optional<OperationRoom> room = operationRoomRepository.findById(req.getRoom());
        req.getDoctors().forEach(doctor ->{
            Optional<Doctor> doctorOptional = doctorRepository.findById(doctor);
            doctorOptional.ifPresent(doctors::add);
        });
        req.getNurses().forEach(nurse ->{
            Optional<User> optionalUser = userRepository.findById(nurse);
            optionalUser.ifPresent(nurses::add);
        });
        if(user.isPresent() && room.isPresent()){
            Operation save = operationRepository.save(Operation.of(req, room.get(), user.get(), nurses, doctors));
            return ResponseEntity.ok(Operation.dto(save));
        }
        else return ResponseEntity.ok("error");
    }
}
