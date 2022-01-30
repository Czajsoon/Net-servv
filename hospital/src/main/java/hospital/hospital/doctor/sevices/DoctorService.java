package hospital.hospital.doctor.sevices;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.doctor.repository.DoctorRepository;
import hospital.hospital.visit.entity.Visit;
import hospital.hospital.visit.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Value("${clinic.visit.time}")
    private Long visitTime;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VisitRepository visitRepository;

    public List<Visit> doctorVisitsBetweenDate(LocalDateTime dateTime, Doctor doctor){
        List<Visit> visits = visitRepository.visitDoctorBeetweenDate(doctor.getId(), dateTime.minusMinutes(visitTime).plusSeconds(1),dateTime.plusMinutes(25L).minusSeconds(1));
        return visits;
    }
}
