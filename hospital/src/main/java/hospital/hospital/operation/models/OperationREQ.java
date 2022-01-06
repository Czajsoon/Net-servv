package hospital.hospital.operation.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hospital.hospital.doctor.entity.Doctor;
import hospital.hospital.operationRoom.entity.OperationRoom;
import hospital.hospital.stay.entity.Stay;
import hospital.hospital.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class OperationREQ {
    private String description;
    private Date date;
    private Set<Long> Doctors;
    private Long user;
    private Set<Long> nurses;
    private Long room;
    private Long stay;

}
