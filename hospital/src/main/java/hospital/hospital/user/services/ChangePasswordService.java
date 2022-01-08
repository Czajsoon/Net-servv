package hospital.hospital.user.services;


import hospital.hospital.user.entity.User;
import hospital.hospital.user.models.ChangePasswordDTO;
import hospital.hospital.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangePasswordService {

@Autowired
    private UserRepository userRepository;

@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

public Boolean changePassword(ChangePasswordDTO changePasswordDTO)
{
    Optional<User> optionalUser = userRepository.findById(changePasswordDTO.getId());


    if(optionalUser.isPresent()){
        if(bCryptPasswordEncoder.matches(changePasswordDTO.getOldPassword(),optionalUser.get().getPassword())){
            optionalUser.get().setPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));
            userRepository.save(optionalUser.get());

            return true;

        }
        else{

            return false;
        }


    }
    else{
        return false;
    }

}


}
