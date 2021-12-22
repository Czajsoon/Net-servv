package hospital.hospital.visitType.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hospital.hospital.visit.entity.Visit;
import hospital.hospital.visitType.models.VisitTypeREQ;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "VISIT_TYPE")
@Data
public class VisitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "visitType")
    private Set<Visit> visits;

    public static VisitType of(VisitTypeREQ req){
        VisitType visitType = new VisitType();
        visitType.setName(req.getName());
        return visitType;
    }

    public static VisitType response(VisitType visitType){
        VisitType visitType1 = new VisitType();
        visitType1.setName(visitType.getName());
        visitType1.setId(visitType.getId());
        Set<Visit> visits = new HashSet<>();
        visitType.getVisits().forEach(visit -> {
            visit.setVisitType(null);
            visits.add(visit);
        });
        visitType1.setVisits(visits);
        return visitType1;
    }
}
