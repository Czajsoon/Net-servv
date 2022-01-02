package hospital.hospital.blood.models;

import hospital.hospital.blood.entity.Blood;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BloodResults extends BloodMaker{
    private List<BloodElement> bloodElements = new ArrayList<>();
}
