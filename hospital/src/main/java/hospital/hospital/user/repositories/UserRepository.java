package hospital.hospital.user.repositories;

import hospital.hospital.role.entity.Role;
import hospital.hospital.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Optional<User> findByIdentification(Long identification);
    List<User> findByRole(Role role);
}
