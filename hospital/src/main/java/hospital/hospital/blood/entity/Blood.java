package hospital.hospital.blood.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.blood.models.BloodBase;
import hospital.hospital.blood.models.BloodREQ;
import hospital.hospital.user.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "BLOOD_RESULTS")
@Data
public class Blood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    private Float hematocrit;
    private Float hemoglobin;
    private Float leukocytes;
    private Float basophils;
    private Float eosinophils;
    private Float neutrophils;
    private Float lymphocytes;
    private Float monocytes;
    private Float erythrocytes;
    private Float thrombocytes;
    private Float redCellVolume;
    private Float averageVolumeCell;
    private Float averageHemoglobinContent;
    private Float hemoglobinConcentration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Blood of(BloodREQ req,User user){
        Blood blood = new Blood();
        blood.setUser(user);
        blood.setDate(req.getDate());
        blood.setHematocrit(req.getHematocrit());
        blood.setHemoglobin(req.getHemoglobin());
        blood.setLeukocytes(req.getLeukocytes());
        blood.setBasophils(req.getBasophils());
        blood.setEosinophils(req.getEosinophils());
        blood.setNeutrophils(req.getNeutrophils());
        blood.setLymphocytes(req.getLymphocytes());
        blood.setMonocytes(req.getMonocytes());
        blood.setErythrocytes(req.getErythrocytes());
        blood.setThrombocytes(req.getThrombocytes());
        blood.setRedCellVolume(req.getRedCellVolume());
        blood.setAverageVolumeCell(req.getAverageVolumeCell());
        blood.setAverageHemoglobinContent(req.getAverageHemoglobinContent());
        blood.setHemoglobinConcentration(req.getHemoglobinConcentration());
        return blood;
    }

    public static Blood dto(Blood blood){
        blood.setUser(null);
        return blood;
    }


}
