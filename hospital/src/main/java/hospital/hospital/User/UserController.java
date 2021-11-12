package hospital.hospital.User;

import hospital.hospital.Privilege.Privilege;
import hospital.hospital.Privilege.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;


    @GetMapping
    public ResponseEntity<List<User>> users(){
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> user(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(userRepository.findById(id).get());
        }
        catch (NoSuchElementException ex){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> user(@RequestBody UserDTO dto){
        try {
            Optional<Privilege> privilege = privilegeRepository.findById(dto.getPrivilegeId());
            User user = userRepository.save(User.of(dto,privilege.get()));
            privilege.get().getUsers().add(user);
            privilegeRepository.save(privilege.get());
            return ResponseEntity.ok().body("User successfully uploaded!");
        }
        catch (NoSuchElementException ex){
            return ResponseEntity.ok().body("User failed to uploaded!");
        }
    }

    @PostConstruct()
    void init(){
        UserDTO user = UserDTO.builder().id(123456L).name("Jakub").surname("Czajkowski").password("123").build();
        Optional<Privilege> priv  = privilegeRepository.findById(3L);
        User uss = userRepository.save(User.of(user,priv.get()));
    }

}
