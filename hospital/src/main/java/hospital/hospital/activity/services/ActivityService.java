package hospital.hospital.activity.services;

import hospital.hospital.activity.models.Activity;
import hospital.hospital.stay.controllers.StayController;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.stay.repository.StayRepository;
import hospital.hospital.visit.controllers.VisitController;
import hospital.hospital.visit.entity.Visit;
import hospital.hospital.visit.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private StayRepository stayRepository;

    public List<Activity> processAllActrivities(){
        List<Activity> activities = new ArrayList<>();
        List<Visit> visits = visitRepository.findAll().stream().map(Visit::dto).collect(Collectors.toList());
        List<Stay> stays = stayRepository.findAll().stream().map(Stay::dto).collect(Collectors.toList());
        visits.forEach(visit -> {
            Activity activity = new Activity();
            activity.setActivity(visit);
            activity.setType("Wizyta");
            activities.add(activity);
        });
        stays.forEach(stay -> {
            Activity activity = new Activity();
            activity.setActivity(stay);
            activity.setType("Pobyt");
            activities.add(activity);
        });
        return activities;
    }

}
