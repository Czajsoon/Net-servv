package hospital.hospital;

import hospital.hospital.model.JwtRequest;
import hospital.hospital.model.JwtResponse;
import hospital.hospital.service.CustomUserDetailsService;
import hospital.hospital.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generateToken")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getPassword());
        authenticationManager.authenticate(upat);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse = JwtResponse.builder().token(token).build();
        return ResponseEntity.ok(jwtResponse);
    }
}
