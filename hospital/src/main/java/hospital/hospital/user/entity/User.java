package hospital.hospital.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.user.models.UserDTO;
import hospital.hospital.user.models.UserModel;
import hospital.hospital.role.entity.Role;
import hospital.hospital.visit.entity.Visit;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private Date bornDate;
    @Column
    private String password;

    @JsonManagedReference//helps avoid circular dependency in bidirectional mapping
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE")
    private Set<Role> role = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Visit> visits;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Recipe> recipes;

    public static User dto(User user){
        if(user.getVisits() != null)user.getVisits().forEach(visit -> {
            visit.setVisitType(null);
            visit.setDoctor(null);
            visit.setUser(null);
        });
        if(user.getRole() != null) user.getRole().forEach(role1 -> role1.setUsers(null));
        return user;
    }

    public static User of(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setBornDate(userDTO.getBornDate());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static User of(UserModel userModel){
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setName(userModel.getName());
        user.setSurname(userModel.getSurname());
        user.setBornDate(userModel.getBornDate());
        user.setPassword(userModel.getPassword());
        return user;
    }

}
