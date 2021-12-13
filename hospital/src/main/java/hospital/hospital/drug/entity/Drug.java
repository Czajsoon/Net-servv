package hospital.hospital.drug.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.drug.models.DrugDTO;
import hospital.hospital.recipe.entity.Recipe;
import hospital.hospital.stay.entity.Stay;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Data
@Table(name = "DRUG")
@ToString
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

    @Column
    private Integer consumableDays;

    @JsonIgnore
    @ManyToMany(mappedBy = "drugs")
    private Set<Recipe> recipes;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Set<Stay> stays;//TODO cos tutaj jest nie tak

    public static Drug of(DrugDTO dto){
        Drug drug = new Drug();
        drug.setDrug_name(dto.getDrug_name());
        drug.setAmount_in_warehouse(dto.getAmount_in_warehouse());
        drug.setPrice(dto.getPrice());
        drug.setConsumableDays(dto.getConsumableDays());
        return drug;
    }

    public static Drug dto(Drug drug){
        drug.setRecipes(null);
        drug.setStays(null);
        return drug;
    }
}
