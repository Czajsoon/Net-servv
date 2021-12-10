package hospital.hospital.blood.repository;

import hospital.hospital.blood.entity.Blood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRepository extends JpaRepository<Blood,Long> {
}
