package hospital.hospital.jwt.controllers;

import hospital.hospital.role.models.RoleDTO;
import hospital.hospital.user.models.UserDTO;
import hospital.hospital.jwt.model.JwtRequest;
import hospital.hospital.jwt.model.JwtResponse;
import hospital.hospital.jwt.services.CustomUserDetailsService;
import hospital.hospital.jwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
        UserDTO register = customUserDetailsService.register(userDTO);
        ResponseEntity<UserDTO> re = new ResponseEntity<>(register, HttpStatus.CREATED);
        return re;
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getPassword());
        authenticationManager.authenticate(upat);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse = JwtResponse.builder().token(token).build();
        return ResponseEntity.ok(jwtResponse);
    }

    @GetMapping("/currentUser")
    public UserDTO getCurrentUser(Principal principal){
        UserDetails userDTO = this.customUserDetailsService.loadUserByUsername(principal.getName());
        return (UserDTO) userDTO;
    }

    @PostConstruct
    private void init(){
        UserDTO admin = new UserDTO();
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setUsername("admin");
        admin.setPassword("admin");
        Set<RoleDTO> roles = new HashSet<>();
        RoleDTO role1 = RoleDTO.builder().id(1L).build();
        RoleDTO role2 = RoleDTO.builder().id(2L).build();
        RoleDTO role3 = RoleDTO.builder().id(3L).build();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        admin.setRoles(roles);
        register(admin);
    }

}
