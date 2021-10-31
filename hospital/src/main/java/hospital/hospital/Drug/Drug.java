package hospital.hospital.Drug;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String drug_name;

    @Column
    private Integer amount_in_warehouse;

    @Column
    private Float price;

    public static Drug of(DrugDTO dto){
        Drug drug = new Drug();
        drug.setDrug_name(dto.getDrug_name());
        drug.setAmount_in_warehouse(dto.getAmount_in_warehouse());
        drug.setPrice(dto.getPrice());
        return drug;
    }
}
