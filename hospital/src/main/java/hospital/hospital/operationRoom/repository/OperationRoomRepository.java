package hospital.hospital.operationRoom.repository;

import hospital.hospital.operation.entity.Operation;
import hospital.hospital.operationRoom.entity.OperationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRoomRepository extends JpaRepository<OperationRoom,Long> {
}
