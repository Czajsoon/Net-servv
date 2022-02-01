package hospital.hospital.doctor.repository;

import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.specialisation.entity.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    @Query(value = "SELECT d FROM Doctor d WHERE d.specialisation = :spec")
    List<Doctor> findBySpecialization(@Param("spec") Specialisation spec);
}
