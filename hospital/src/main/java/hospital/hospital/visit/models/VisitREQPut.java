package hospital.hospital.visit.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitREQPut {
    private Long id;
    private LocalDateTime date;
}
