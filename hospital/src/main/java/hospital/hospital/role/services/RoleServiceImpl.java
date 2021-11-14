package hospital.hospital.role.services;

import hospital.hospital.role.entity.Role;
import hospital.hospital.role.models.RoleDTO;
import hospital.hospital.role.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDTO createRole(RoleDTO role) {
        return RoleDTO.of(roleRepository.save(Role.of(role)));
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream().map(RoleDTO::of).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(Long roleId) {
        //TODO isPresent exception
        return RoleDTO.of(roleRepository.findById(roleId).get());
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
