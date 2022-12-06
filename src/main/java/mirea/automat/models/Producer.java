package mirea.automat.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Data
@ToString(exclude = {"clothes"},includeFieldNames = false)
@Entity
@Table(name="producer")
public class Producer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Producer name should not be empty")
    @Column(name = "name")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "producer")
    private List<Cloth> clothes;
}
