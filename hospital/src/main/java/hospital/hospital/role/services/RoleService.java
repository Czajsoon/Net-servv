package hospital.hospital.role.services;

import hospital.hospital.role.models.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(RoleDTO role);
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Long roleId);
    void deleteRoleById(Long roleId);

}
