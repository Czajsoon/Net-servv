package hospital.hospital.operation.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.operation.entity.Operation;
import hospital.hospital.operation.models.OperationDTO;
import hospital.hospital.operation.models.OperationREQ;
import hospital.hospital.operation.repository.OperationRepository;
import hospital.hospital.operationRoom.entity.OperationRoom;
import hospital.hospital.operationRoom.repository.OperationRoomRepository;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.stay.repository.StayRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private StayRepository stayRepository;

    @PostMapping
    public ResponseEntity<?> operation(@RequestBody OperationREQ req){
        List<Doctor> doctorsSet = new ArrayList<>();
        List<User> nurses = new ArrayList<>();
        req.getDoctors().forEach(doctors -> doctorRepository.findById(doctors).ifPresent(doctorsSet::add));
        req.getNurses().forEach(nurse -> userRepository.findById(nurse).ifPresent(nurses::add));
        Optional<User> user = userRepository.findById(req.getUser());
        Optional<OperationRoom> room = operationRoomRepository.findById(req.getRoom());
        Optional<Stay> stay = stayRepository.findById(req.getStay());
        if(user.isPresent() && room.isPresent() && stay.isPresent()){
            OperationDTO dto = OperationDTO.builder()
                    .date(req.getDate())
                    .description(req.getDescription())
                    .doctorList(doctorsSet)
                    .nursesList(nurses)
                    .patient(user.get())
                    .operationRoom(room.get())
                    .hospitalization(stay.get())
                    .build();
            Operation save = operationRepository.save(Operation.of(dto));
            return ResponseEntity.ok(Operation.dto(save));
        }
        return ResponseEntity.ok("Error");
    }
}
