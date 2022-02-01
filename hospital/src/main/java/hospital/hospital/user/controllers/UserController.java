package hospital.hospital.user.controllers;

import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public ResponseEntity<?> users(){
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setVisits(null);
        });
        return ResponseEntity.ok(users);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> userDELETE(@PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().body(userRepository.findAll());
    }







    @PostConstruct
    public void init(){
//        UserDTO user = new UserDTO.builder();
    }
}
