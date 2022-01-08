package hospital.hospital.visit.services;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.visit.entity.Visit;
import hospital.hospital.visit.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {
    @Value("${clinic.visit.time}")
    private Long visitTime;
    @Value("${clinic.open.hour}")
    private Integer clinicStart;
    @Value("${clinic.close.hour}")
    private Integer clinicEnd;
    @Autowired
    private VisitRepository visitRepository;

    public List<Visit> filterVisits(String spec,
                                    String doctorSurname,
                                    String userSurname,
                                    String date,
                                    String endDate){
        List<Visit> visits;
        if(date != null && endDate != null){
            visits = visitRepository.visitsBetween(LocalDateTime.parse(endDate),LocalDateTime.parse(date));
        }
        else if(date != null){
            visits = visitRepository.findAllByStartDateAfterOrderByStartDateAsc(LocalDateTime.parse(date));
        }
        else if(endDate!=null){
            visits = visitRepository.findAllByStartDateBeforeOrderByStartDateDesc(LocalDateTime.parse(endDate));
        }
        else visits = visitRepository.findAllByStartDateAfterOrderByStartDateAsc(LocalDateTime.now());
        //filtering if doctor specialization is present
        if(!spec.equals("Wszystkie"))
            visits = visits
                    .stream()
                    .filter(visit -> visit
                            .getDoctor().getSpecialisation().stream()
                            .anyMatch(specialisation -> specialisation.getName().equals(spec)))
                    .collect(Collectors.toList());
        //filtering if doctor surname is present
        if (doctorSurname != null)
            visits = visits
                    .stream()
                    .filter((visit -> visit.getDoctor().getSurname().contains(doctorSurname)))
                    .collect(Collectors.toList());
        //filtering if user surname is present
        if (userSurname != null)
            visits = visits
                    .stream()
                    .filter((visit -> visit.getUser().getSurname().contains(userSurname)))
                    .collect(Collectors.toList());
        return visits.stream().map(Visit::dto).collect(Collectors.toList());
    }

    private LocalDateTime findMaxDateVisit(Doctor doctor,LocalDateTime date){
        while(visitRepository.visitDoctorBeetweenDate(doctor.getId(),date,date.plusMinutes(visitTime).minusSeconds(1)).size() > 0){
            date = date.plusMinutes(visitTime).minusSeconds(1);
        }
        return date.plusSeconds(1);
    }

    public LocalDateTime nearestVisitDate(Doctor doctor,LocalDateTime dateTime){
        return findMaxDateVisit(doctor,dateTime);
    }
}
