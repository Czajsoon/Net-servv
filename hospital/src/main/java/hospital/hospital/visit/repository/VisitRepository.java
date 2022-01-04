package hospital.hospital.visit.repository;

import hospital.hospital.visit.entity.Visit;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    List<Visit> findAllByStartDateAfterOrderByStartDateAsc(LocalDateTime startDate);
    List<Visit> findAllByStartDateBeforeOrderByStartDateDesc(LocalDateTime endDate);
    @Query(value = "SELECT v FROM Visit v WHERE v.startDate BETWEEN :date AND :endDate order by v.startDate ASC")
    List<Visit> visitsBetween(@Param("endDate") LocalDateTime endDate, @Param("date") LocalDateTime date);
}
