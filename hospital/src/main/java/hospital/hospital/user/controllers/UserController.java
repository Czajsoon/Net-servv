package hospital.hospital.user.controllers;

import hospital.hospital.jwt.controllers.JwtController;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.models.UserDTO;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtController jwtController;

    @GetMapping
    public ResponseEntity<?> users(){
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            user.setVisits(null);
        });
        return ResponseEntity.ok(users);
    }

    @PostConstruct
    public void init(){
//        UserDTO user = new UserDTO.builder();
    }
}
