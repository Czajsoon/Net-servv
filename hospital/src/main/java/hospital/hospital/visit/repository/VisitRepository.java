package hospital.hospital.visit.repository;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.visit.entity.Visit;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long> {
    Optional<Visit> findVisitByStartDateAndAndDoctor(LocalDateTime time, Doctor doctor);
    List<Visit> findAllByStartDateAfterOrderByStartDateAsc(LocalDateTime startDate);
    List<Visit> findAllByStartDateBeforeOrderByStartDateDesc(LocalDateTime endDate);
    @Query(value = "SELECT v FROM Visit v WHERE v.startDate BETWEEN :date AND :endDate order by v.startDate ASC")
    List<Visit> visitsBetween(@Param("endDate") LocalDateTime endDate, @Param("date") LocalDateTime date);
    @Query(value = "SELECT v FROM Visit v WHERE" +
            " v.doctor.id=:doctor " +
            "AND v.startDate BETWEEN :startDate AND :endDate ")
    List<Visit> visitDoctorBeetweenDate(
            @Param("doctor") Long doctor,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
    @Query(value = "SELECT v from Visit v where v.startDate = (select MAX(vv.startDate) FROM Visit vv)")
    Visit findMaxVisitDateTime();
    @Query(value = "SELECT v from Visit v where v.startDate = (select MIN(vv.startDate) FROM Visit vv)")
    Visit findMinVisitDateTime();
}
