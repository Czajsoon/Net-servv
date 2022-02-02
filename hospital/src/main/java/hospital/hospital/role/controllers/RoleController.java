package hospital.hospital.role.controllers;

import hospital.hospital.role.models.RoleDTO;
import hospital.hospital.role.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO role){
        return roleService.createRole(role);
    }

    @GetMapping
    public List<RoleDTO> getAllRules(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getRule(@PathVariable Long id){
        return roleService.getRoleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long roleId){
        roleService.deleteRoleById(roleId);
    }

    @PostConstruct
    private void init(){
        RoleDTO admin = RoleDTO.builder().name("ADMIN").build();
        RoleDTO user = RoleDTO.builder().name("USER").build();
        RoleDTO doctor = RoleDTO.builder().name("DOCTOR").build();
        RoleDTO headingDoctor = RoleDTO.builder().name("HEADING DOCTOR").build();
        RoleDTO receptionist = RoleDTO.builder().name("RECEPTIONIST").build();
        createRole(admin);
        createRole(user);
        createRole(doctor);
        createRole(headingDoctor);
        createRole(receptionist);
    }

}
