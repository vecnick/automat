package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"defect","gost","clothes"},includeFieldNames = false)
@Entity
@Table(name="quality")
public class Quality {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "condition should not be empty")
    @Column(name = "condition")
    @NonNull
    private String condition;

    @NotEmpty(message = "suitability should not be empty")
    @Column(name = "suitability")
    @NonNull
    private String suitability;

    @ManyToOne
    @JoinColumn(name="defect_id", referencedColumnName = "id")
    private Defect defect;

    @ManyToOne
    @JoinColumn(name="gost_id", referencedColumnName = "id")
    private Gost gost;

    @OneToMany(mappedBy = "quality")
    private List<Cloth> clothes;
}
