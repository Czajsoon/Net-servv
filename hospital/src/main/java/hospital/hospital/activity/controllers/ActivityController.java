package hospital.hospital.activity.controllers;

import hospital.hospital.activity.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<?> activities(){
        return ResponseEntity.ok(activityService.processAllActrivities());
    }
}
