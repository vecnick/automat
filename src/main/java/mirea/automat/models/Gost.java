package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"qualities","clothes"},includeFieldNames = false)
@Entity
@Table(name="gost")
public class Gost {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "GOST name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @NotEmpty(message = "Enter description")
    @Column(name = "description")
    @NonNull
    private String description;

    @OneToMany(mappedBy = "gost")
    private List<Quality> qualities;

    @OneToMany(mappedBy = "gost")
    private List<Cloth> clothes;
}
