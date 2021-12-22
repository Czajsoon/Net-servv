package hospital.hospital.doctor.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.models.DoctorREQ;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.jwt.controllers.JwtController;
import hospital.hospital.specialisation.entity.Specialisation;
import hospital.hospital.specialisation.repository.SpecialisationRepository;
import hospital.hospital.user.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController{
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SpecialisationRepository specialisationRepository;
    @Autowired
    private JwtController jwtController;


    @PostMapping
    public ResponseEntity<?> doctor(@RequestBody DoctorREQ doctorREQ){
        ResponseEntity<UserDTO> register = jwtController.register(doctorREQ);
        Doctor doctor = Doctor.of(doctorREQ);
        doctor.setId(register.getBody().getId());
        List<Specialisation> specs = new ArrayList<>();
        doctorREQ.getSpecialisations().forEach(special -> specs.add(specialisationRepository.findById(special).get()));
        doctor.setSpecialisation(specs);
        Doctor save = doctorRepository.save(doctor);
        return ResponseEntity.ok(Doctor.dto(save));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> doctor(){
        return ResponseEntity.ok(doctorRepository.findAll().stream().map(Doctor::dto).collect(Collectors.toList()));
    }
}
