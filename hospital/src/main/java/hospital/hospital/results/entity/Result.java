package hospital.hospital.results.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.user.entity.User;
import lombok.Data;

import javax.persistence.*;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "RESULT_TEST")
@Data
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
