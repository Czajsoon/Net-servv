package hospital.hospital.visit.models;

import lombok.Data;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class VisitREQ {
    private LocalDateTime date;
    private String description;
    private Long visitType;
    private Long doctor;
    private Long user;
}
