package hospital.hospital.specialisation.repository;

import hospital.hospital.specialisation.entity.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialisationRepository extends JpaRepository<Specialisation,Long> {
}
