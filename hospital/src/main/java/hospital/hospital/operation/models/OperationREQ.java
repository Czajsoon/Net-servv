package hospital.hospital.operation.models;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class OperationREQ {
    private String description;
    private Date date;
    private Set<Long> Doctors;
    private Long user;
    private Set<Long> nurses;
    private Long room;
}
