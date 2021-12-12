package hospital.hospital.operationRoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.operation.entity.Operation;
import hospital.hospital.operationRoom.models.OperationRoomREQ;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "OPERATION_ROOM")
@Data
public class OperationRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer roomNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "operationRoom")
    private Set<Operation> operations;

    public static OperationRoom of(OperationRoomREQ req){
        OperationRoom operationRoom = new OperationRoom();
        operationRoom.setRoomNumber(req.getRoomNumber());
        return operationRoom;
    }
}
