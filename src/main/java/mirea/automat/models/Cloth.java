package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"producer","textile","quality","gost","orders"},includeFieldNames = false)
@Entity
@Table(name="cloth")
public class Cloth {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Cloth name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Enter size")
    @Column(name = "size")
    @NonNull
    private String size;

    @ManyToOne
    @JoinColumn(name="producer_id", referencedColumnName = "id")
    private Producer producer;

    @ManyToOne
    @JoinColumn(name="textile_id", referencedColumnName = "id")
    private Textile textile;

    @ManyToOne
    @JoinColumn(name="quality_id", referencedColumnName = "id")
    private Quality quality;

    @ManyToOne
    @JoinColumn(name="gost_id", referencedColumnName = "id")
    private Gost gost;

    @OneToMany(mappedBy = "cloth")
    private List<Order> orders;
}
