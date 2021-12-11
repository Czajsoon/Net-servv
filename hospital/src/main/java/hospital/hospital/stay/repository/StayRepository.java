package hospital.hospital.stay.repository;

import hospital.hospital.stay.entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayRepository extends JpaRepository<Stay,Long> {
}
