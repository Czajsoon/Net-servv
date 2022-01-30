package hospital.hospital.user.controllers;

import hospital.hospital.jwt.controllers.JwtController;
import hospital.hospital.role.entity.Role;
import hospital.hospital.role.repositories.RoleRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private RoleRepository roleRepository;
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

    @GetMapping("/{roleId}")
    public ResponseEntity<?> users(@PathVariable Long roleId){
        Optional<Role> role = roleRepository.findById(roleId);
        if(role.isPresent()){
            List<User> userList = userRepository.findByRole(role.get());
            return ResponseEntity.ok(userList);
        }
        else{
            return ResponseEntity.ok("Rola nie została znaleziona!");
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> userDELETE(@PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().body(userRepository.findAll());
    }




}
