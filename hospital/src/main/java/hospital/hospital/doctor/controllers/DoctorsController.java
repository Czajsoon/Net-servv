package hospital.hospital.doctor.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.models.DoctorsREQ;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.specialisation.entity.Specialisation;
import hospital.hospital.specialisation.repository.SpecialisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {

    @Autowired
    private DoctorController doctorController;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SpecialisationRepository specialisationRepository;


    @PostMapping
    public ResponseEntity<?> doctors(@RequestBody DoctorsREQ doctorsREQS) {
        doctorsREQS.getDoctors().forEach(doctor -> doctorController.doctor(doctor));
        return ResponseEntity.ok("Doctors Added!");
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> doctor(@RequestParam String spec) {
        List<Doctor> doctors = doctorRepository.findAll();
        doctors = doctors.stream().filter(doctor->doctor.getSpecialisation().stream().anyMatch(specialisation -> specialisation.getName().equals(spec))).collect(Collectors.toList());
        return ResponseEntity.ok(doctors.stream().map(Doctor::dto).collect(Collectors.toList()));

    }

}