package hospital.hospital.visit.controllers;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.doctor.sevices.DoctorService;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import hospital.hospital.visit.entity.Visit;
import hospital.hospital.visit.models.VisitREQ;
import hospital.hospital.visit.models.VisitREQPut;
import hospital.hospital.visit.repository.VisitRepository;
import hospital.hospital.visit.services.VisitService;
import hospital.hospital.visitType.entity.VisitType;
import hospital.hospital.visitType.repository.VisitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    @Value("${clinic.visit.time}")
    private Long visitTime;
    @Value("${clinic.open.hour}")
    private Integer clinicStart;
    @Value("${clinic.close.hour}")
    private Integer clinicEnd;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private VisitTypeRepository visitTypeRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VisitService visitService;
    @Autowired
    private DoctorService doctorService;

    @PutMapping
    public ResponseEntity<?> visit(@RequestBody VisitREQPut editedVisit){
        Optional<Visit> visit = visitRepository.findById(editedVisit.getId());
        if(visit.isPresent()){
            Optional<Visit> visit1 = visitRepository.findVisitByStartDateAndAndDoctor(editedVisit.getDate(), visit.get().getDoctor());
            if (!visit1.isPresent()){
                visit.get().setStartDate(editedVisit.getDate());
                visitRepository.save(visit.get());
                return ResponseEntity.ok("Pomyślnie zmieniono date wizyty!");
            }
            else {
                return ResponseEntity.ok("Najbliższa możliwa wizyta jest: " + visitService.nearestVisitDate(visit.get().getDoctor(), editedVisit.getDate()).toString());
            }
        }
        else return ResponseEntity.ok("Visit not Found!");
    }

    @CrossOrigin("*")
    @PostMapping
    public ResponseEntity<?> visits(@RequestBody VisitREQ visitREQ){
        Optional<VisitType> visitType = visitTypeRepository.findById(visitREQ.getVisitType());
        Optional<Doctor> doctor = doctorRepository.findById(visitREQ.getDoctor());
        Optional<User> user = userRepository.findByIdentification(visitREQ.getUser());
        if(visitType.isPresent() && doctor.isPresent() && user.isPresent()){
            if (visitREQ.getDate().isBefore(LocalDateTime.of(visitREQ.getDate().toLocalDate(), LocalTime.of(clinicStart,0,0))) ||
                    visitREQ.getDate().isAfter(LocalDateTime.of(visitREQ.getDate().toLocalDate(), LocalTime.of(clinicEnd,0,0)))){
                return ResponseEntity.ok().body("Clinic is closed! Clinic is open " + clinicStart + ":00-" + clinicEnd + ":00");
            }
            else{
                Optional<Visit> optionalVisit = visitRepository.findVisitByStartDateAndAndDoctor(visitREQ.getDate(), doctor.get());
                if(!optionalVisit.isPresent()){
                    Visit visit = visitRepository.save(Visit.of(visitREQ, visitType.get(), doctor.get(), user.get()));
                    return ResponseEntity.ok(Visit.dto(visit));
                }
                else {
                    return ResponseEntity.ok("Wizyta w tej godzinie jest niedostępna!");
                }
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Visit>> visits(){
        List<Visit> visits = visitRepository.findAll();
        visits = visits.stream().map(Visit::dto).collect(Collectors.toList());
        return ResponseEntity.ok(visits);
    }

    @CrossOrigin("*")
    @DeleteMapping
    public ResponseEntity<?> visit(@RequestParam String visitId){
        visitRepository.deleteById(Long.parseLong(visitId));
        return ResponseEntity.ok("Wizyta została pomyślnie anulowana!");
    }

    @GetMapping("/receptionist")
    public ResponseEntity<?> receptionistVisits(
            @RequestParam String spec,
            @RequestParam(required = false) String doctorSurname,
            @RequestParam(required = false) String userSurname,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String endDate){
        return ResponseEntity.ok(visitService.filterVisits(spec, doctorSurname, userSurname, date, endDate));
    }
}
