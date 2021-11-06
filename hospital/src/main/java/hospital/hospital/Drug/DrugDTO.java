package hospital.hospital.Drug;

import lombok.Data;

@Data
public class DrugDTO {
    private String drug_name;
    private Integer amount_in_warehouse;
    private Float price;
}

