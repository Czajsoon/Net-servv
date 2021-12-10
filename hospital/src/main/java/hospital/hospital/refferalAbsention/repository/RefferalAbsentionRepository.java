package hospital.hospital.refferalAbsention.repository;

import hospital.hospital.refferalAbsention.entity.RefferalAbsention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefferalAbsentionRepository extends JpaRepository<RefferalAbsention,Long> {
}
