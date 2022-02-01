package hospital.hospital.user.controllers;

import hospital.hospital.jwt.controllers.JwtController;
import hospital.hospital.role.entity.Role;
import hospital.hospital.role.repositories.RoleRepository;
import hospital.hospital.user.entity.User;
import hospital.hospital.user.models.UserDTO;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> userDELETE(@PathVariable Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("Użytkownik Pomyślnie usunięty!");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> userUpdate(@PathVariable Long id, @RequestBody UserDTO userDTO){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = User.put(userDTO, optionalUser.get());
            userRepository.save(user);
            return ResponseEntity.ok("Użytkownik został zaktualizowany");
        }
        else return ResponseEntity.ok().body("Uzytkownik nie znaleziony!");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/forceChangePassword/{id}")
    public ResponseEntity<?> forceChangePassword(@PathVariable Long id,@RequestParam(name = "newPassword") String newPassword){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            optionalUser.get().setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(optionalUser.get());
            return ResponseEntity.ok("Hasło użytkownika zostało zaktualizowane");
        }
        else return ResponseEntity.status(404).body("Uzytkownik nie znaleziony!");
    }


}
