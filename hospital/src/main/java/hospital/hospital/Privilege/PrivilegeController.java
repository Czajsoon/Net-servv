package hospital.hospital.Privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
    @Autowired
    PrivilegeRepository privilegeRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Privilege> privilege(@PathVariable Long id){
        return(privilegeRepository.findById(id).isPresent()
                    ? ResponseEntity.ok().body(privilegeRepository.findById(id).get())
                    : ResponseEntity.ok().body(null));
    }

    @PostConstruct
    private void init(){
        PrivilegeDTO p1 = PrivilegeDTO.builder().name("Pacjent").build();
        PrivilegeDTO p2 = PrivilegeDTO.builder().name("Lekarz").build();
        PrivilegeDTO p3 = PrivilegeDTO.builder().name("Lekarz Ordynator").build();
        privilegeRepository.save(Privilege.of(p1));
        privilegeRepository.save(Privilege.of(p2));
        privilegeRepository.save(Privilege.of(p3));
    }
}
