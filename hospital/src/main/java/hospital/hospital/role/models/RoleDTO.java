package hospital.hospital.role.models;

import hospital.hospital.role.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private Long id;
    private String name;

    public static RoleDTO of(Role role){
        return RoleDTO.builder().name(role.getName()).id(role.getId()).build();
    }
}
