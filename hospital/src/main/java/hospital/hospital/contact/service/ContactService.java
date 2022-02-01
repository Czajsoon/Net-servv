package hospital.hospital.contact.service;


import hospital.hospital.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private JavaMailSender javaMailSender;


    public void sendNotification(Contact contact) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("hospita.pip@gmail.com");
        mail.setTo("hospita.pip@gmail.com");
        mail.setSubject(contact.getMessageTitle());
        mail.setText("User email: " + contact.getUserMailAddress() + "\n" +contact.getDescription() + "\n" +contact.getNameusername());
        javaMailSender.send(mail);
    }

}
