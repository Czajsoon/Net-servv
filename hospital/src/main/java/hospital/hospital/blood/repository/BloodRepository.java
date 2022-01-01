package hospital.hospital.blood.repository;

import hospital.hospital.blood.entity.Blood;
import hospital.hospital.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodRepository extends JpaRepository<Blood,Long> {
    //get newest blood test user
    @Query(value = "SELECT b FROM Blood b WHERE b.user=:#{#givenUser} and b.date = (select max(bl.date) FROM Blood bl)")
    Blood max(@Param("givenUser") User user);
}
