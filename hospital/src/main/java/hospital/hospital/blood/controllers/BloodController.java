package hospital.hospital.blood.controllers;

import hospital.hospital.blood.entity.Blood;
import hospital.hospital.blood.models.BloodREQ;
import hospital.hospital.blood.repository.BloodRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/blood")
public class BloodController {
    @Autowired
    private BloodRepository bloodRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> blood(@RequestBody BloodREQ req){
        Optional<User> user = userRepository.findById(req.getUser());
        if(user.isPresent()){
            Blood save = bloodRepository.save(Blood.of(req, user.get()));
            return ResponseEntity.ok(save);
        }
        else return ResponseEntity.ok("error");
    }

}
