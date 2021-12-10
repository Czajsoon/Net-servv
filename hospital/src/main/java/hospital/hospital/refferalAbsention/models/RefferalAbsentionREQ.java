package hospital.hospital.refferalAbsention.models;

import lombok.Data;

import java.util.Date;

@Data
public class RefferalAbsentionREQ {
    private Date startDate;
    private Date endDate;
    private String description;
    private Long doctor;
    private Long user;
    private Long visit;
}
