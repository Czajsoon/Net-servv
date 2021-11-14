package hospital.hospital.drug.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DrugDTO {
    private String drug_name;
    private Integer amount_in_warehouse;
    private Float price;
}

