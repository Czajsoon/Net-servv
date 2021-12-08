package hospital.hospital.visitType.repository;

import hospital.hospital.visitType.entity.VisitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitTypeRepository extends JpaRepository<VisitType,Long> {
}
