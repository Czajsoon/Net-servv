package hospital.hospital.refferalAbsention.controllers;

import hospital.hospital.refferalAbsention.repository.RefferalAbsentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refferalAbsention")
public class RefferalAbsentionController {
    @Autowired
    private RefferalAbsentionRepository refferalAbsentionRepository;

    @PostMapping
    public ResponseEntity<?> ra(){
        return ResponseEntity.ok("OK");
    }

}
