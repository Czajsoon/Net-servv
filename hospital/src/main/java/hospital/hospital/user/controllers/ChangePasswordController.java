package hospital.hospital.user.controllers;



import hospital.hospital.user.models.ChangePasswordDTO;
import hospital.hospital.user.repositories.UserRepository;
import hospital.hospital.user.services.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/change")
public class ChangePasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChangePasswordService changePasswordService;




    @PostMapping
    public ResponseEntity changePOST(@RequestBody ChangePasswordDTO passDTO){


        if(changePasswordService.changePassword(passDTO))
        {
            return ResponseEntity.ok("zmieniono hasło");
        }
        else{
            return ResponseEntity.ok("nie udało sie zmienić hasla");
        }

    }









}
