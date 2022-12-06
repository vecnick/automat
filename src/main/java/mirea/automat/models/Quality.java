package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
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



    @ManyToOne
    @JoinColumn(name="defect_id", referencedColumnName = "id")
    private Defect defect;

    @ManyToOne
    @JoinColumn(name="gost_id", referencedColumnName = "id")
    private Gost gost;

    @OneToMany(mappedBy = "quality")
    private List<Cloth> clothes;
}
