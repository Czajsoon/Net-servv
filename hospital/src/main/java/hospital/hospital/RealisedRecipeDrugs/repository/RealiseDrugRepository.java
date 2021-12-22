package hospital.hospital.RealisedRecipeDrugs.repository;

import hospital.hospital.RealisedRecipeDrugs.entity.RealisedDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealiseDrugRepository extends JpaRepository<RealisedDrug,Long> {
}
