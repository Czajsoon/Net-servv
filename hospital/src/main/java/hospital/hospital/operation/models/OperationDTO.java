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

@Data
@Builder
public class OperationDTO {
    private String description;
    private Date date;
    private List<Doctor> doctorList;
    private List<User> nursesList;
    private User patient;
    private OperationRoom operationRoom;
    private Stay hospitalization;

    public static class builder {
    }
}
