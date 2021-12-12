package hospital.hospital.activity.services;

import hospital.hospital.activity.models.Activity;
import hospital.hospital.stay.controllers.StayController;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.visit.controllers.VisitController;
import hospital.hospital.visit.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ActivityService {
    @Autowired
    private VisitController visitController;
    @Autowired
    private StayController stayController;

    public List<Activity> processAllActrivities(){
        List<Activity> activities = new ArrayList<>();
        List<Visit> visits = visitController.visits().getBody();
        Set<Stay> stays = stayController.stay().getBody();
        visits.forEach(visit -> {
            Activity activity = new Activity();
            activity.setActivity(visit);
            activity.setType("Visit");
            activities.add(activity);
        });
        stays.forEach(stay -> {
            Activity activity = new Activity();
            activity.setActivity(stay);
            activity.setType("Stay");
            activities.add(activity);
        });
        return activities;
    }

}
