package hospital.hospital.results.entity;

import hospital.hospital.user.entity.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
