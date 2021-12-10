package hospital.hospital.operationRoom.controllers;

import hospital.hospital.operationRoom.entity.OperationRoom;
import hospital.hospital.operationRoom.models.OperationRoomREQ;
import hospital.hospital.operationRoom.repository.OperationRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/operationRoom")
public class OperationRoomController {
    @Autowired
    private OperationRoomRepository operationRoomRepository;

    @PostMapping
    public ResponseEntity<?> operationRoom(@RequestBody OperationRoomREQ req){
        OperationRoom save = operationRoomRepository.save(OperationRoom.of(req));
        return ResponseEntity.ok(save);
    }

    @PostConstruct
    public void init(){
        OperationRoomREQ req1 = new OperationRoomREQ();
        OperationRoomREQ req2 = new OperationRoomREQ();
        OperationRoomREQ req3 = new OperationRoomREQ();
        OperationRoomREQ req4 = new OperationRoomREQ();
        req1.setRoomNumber(101);
        req2.setRoomNumber(102);
        req3.setRoomNumber(201);
        req4.setRoomNumber(202);
        operationRoom(req1);
        operationRoom(req2);
        operationRoom(req3);
        operationRoom(req4);
    }
}
