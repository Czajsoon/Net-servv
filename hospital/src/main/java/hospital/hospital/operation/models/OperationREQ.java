package hospital.hospital.operation.models;

import lombok.Data;

import java.util.Set;

@Data
public class OperationREQ {
    private Set<Long> Doctors;
    private Long user;
    private Set<Long> nurses;
    private Long room;
}
