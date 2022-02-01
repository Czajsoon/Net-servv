package hospital.hospital.visit.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitREQ {
    private LocalDateTime date;
    private String description;
    private Long visitType;
    private Long doctor;
    private Long user;
}
