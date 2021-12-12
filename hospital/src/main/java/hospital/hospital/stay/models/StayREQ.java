package hospital.hospital.stay.models;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class StayREQ {
    private String description;
    private Date startDate;
    private Date endDate;
    private Long doctor;
    private Long user;
}
