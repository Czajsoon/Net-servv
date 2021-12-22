package hospital.hospital.recipe.models;

import lombok.Data;

import javax.persistence.Column;
import javax.swing.text.StyledEditorKit;
import java.util.Date;
import java.util.Set;

@Data
public class RecipeREQ {
    private Long user;
    private Long doctor;
    private Set<Long> drugs;
    private Long visit;
    private Date date;
    private Boolean realised;
    private String Description;
}
