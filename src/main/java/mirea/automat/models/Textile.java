package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"clothes"},includeFieldNames = false)
@Entity
@Table(name="textile")
public class Textile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Textile name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Enter textile type")
    @Column(name = "type")
    @NonNull
    private String type;

    @OneToMany(mappedBy = "textile")
    private List<Cloth> clothes;
}
