package hospital.hospital.contact;

import hospital.hospital.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/contact")
public class ContactController {

    @Autowired
    ContactService contactService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity postContact(@RequestBody Contact contact){
        try{
            contactService.sendNotification(contact);
            return ResponseEntity.ok().body("Mail has been send!");
        }
        catch (MailException e){
            return ResponseEntity.badRequest().body("Mail hasn't been send!");
        }
    }
}
