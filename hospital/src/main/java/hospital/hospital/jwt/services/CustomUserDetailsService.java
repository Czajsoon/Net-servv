package hospital.hospital.jwt.services;

import hospital.hospital.user.entity.User;
import hospital.hospital.user.models.UserDTO;
import hospital.hospital.user.repositories.UserRepository;
import hospital.hospital.role.entity.Role;
import hospital.hospital.role.models.RoleDTO;
import hospital.hospital.role.repositories.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserDTO register(@RequestBody UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);//it does not deep copy
        //fetch every role from DB based on role id and then set this roloe to user entity roles
        Set<Role> roles = new HashSet<>();
        userDTO.getRoles().forEach(role -> {
            Optional<Role> optionalRole = roleRepository.findById(role.getId());
            optionalRole.ifPresent(roles::add);
        });
        user.setRole(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = userRepository.save(user);
        BeanUtils.copyProperties(user1,userDTO);
        Set<RoleDTO> roleDTOSet = new HashSet<>();
        user1.getRole().forEach(role -> {
            roleDTOSet.add(RoleDTO.builder().name(role.getName()).id(role.getId()).build());
        });
        userDTO.setRoles(roleDTOSet);
        return userDTO;
    }

    //this method does the validation for user existance
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User does not exists!");
        }
        UserDTO us = new UserDTO();
        BeanUtils.copyProperties(user,us);
        Set<RoleDTO> roleDTOSet = new HashSet<>();
        user.getRole().forEach(role -> {
            roleDTOSet.add(RoleDTO.builder().name(role.getName()).id(role.getId()).build());
        });
        us.setRoles(roleDTOSet);
        return us;
    }
}
