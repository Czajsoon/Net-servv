package hospital.hospital.doctor.controllers;

import hospital.hospital.doctor.models.DoctorsREQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {
    @Autowired
    private DoctorController doctorController;

    @PostMapping
    public ResponseEntity<?> doctors(@RequestBody DoctorsREQ doctorsREQS){
        doctorsREQS.getDoctors().forEach(doctor -> doctorController.doctor(doctor));
        return ResponseEntity.ok("Doctors Added!");
    }
}
