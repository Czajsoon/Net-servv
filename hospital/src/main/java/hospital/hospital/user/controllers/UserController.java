package hospital.hospital.user.controllers;

import hospital.hospital.jwt.controllers.JwtController;
import hospital.hospital.user.models.UserDTO;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;
    private JwtController jwtController;
//    @GetMapping("/doctors")
//    public ResponseEntity<User>

    @PostConstruct
    public void init(){
//        UserDTO user = new UserDTO.builder();
    }
}
