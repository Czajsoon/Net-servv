package hospital.hospital.visit.models;

import lombok.Data;

import java.util.Date;

@Data
public class VisitREQ {
    private Date date;
    private String description;
    private Long visitType;
    private Long doctor;
    private Long user;
}
